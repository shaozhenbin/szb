package com.szb.mongo.oauth;

import com.szb.mongo.oauth.domain.MongoClientDetails;
import com.szb.mongo.oauth.repositories.MongoClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Component
public class MongoClientDetailsService implements ClientDetailsService, ClientRegistrationService {

	private final MongoClientDetailsRepository mongoClientDetailsRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public MongoClientDetailsService(final MongoClientDetailsRepository mongoClientDetailsRepository,
			final PasswordEncoder passwordEncoder) {
		this.mongoClientDetailsRepository = mongoClientDetailsRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		try {
			final MongoClientDetails mongoClientDetails = mongoClientDetailsRepository.findByClientId(clientId);

			BaseClientDetails client = new BaseClientDetails(mongoClientDetails.getClientId(),
					mongoClientDetails.getResourceIds().stream().collect(Collectors.joining(",")),
					mongoClientDetails.getScope().stream().collect(Collectors.joining(",")),
					mongoClientDetails.getAuthorizedGrantTypes().stream().collect(Collectors.joining(",")),
					mongoClientDetails.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.joining(",")),
					mongoClientDetails.getRegisteredRedirectUri().stream().collect(Collectors.joining(",")));

			client.setAccessTokenValiditySeconds(mongoClientDetails.getAccessTokenValiditySeconds());
			client.setRefreshTokenValiditySeconds(mongoClientDetails.getRefreshTokenValiditySeconds());
			client.setClientSecret(mongoClientDetails.getClientSecret());
			
			return client;
		} catch (IllegalArgumentException e) {
			throw new ClientRegistrationException("No Client Details for client id", e);
		}
	}

	@Override
	public void addClientDetails(final ClientDetails clientDetails) throws ClientAlreadyExistsException {
		final MongoClientDetails mongoClientDetails = new MongoClientDetails(clientDetails.getClientId(),
				passwordEncoder.encode(clientDetails.getClientSecret()),
				clientDetails.getScope(),
				clientDetails.getResourceIds(),
				clientDetails.getAuthorizedGrantTypes(),
				clientDetails.getRegisteredRedirectUri(),
				new ArrayList(clientDetails.getAuthorities()),
				clientDetails.getAccessTokenValiditySeconds(),
				clientDetails.getRefreshTokenValiditySeconds(),
				clientDetails.getAdditionalInformation(),
				null);

		mongoClientDetailsRepository.save(mongoClientDetails);
	}

	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
		final MongoClientDetails mongoClientDetails = new MongoClientDetails(clientDetails.getClientId(),
				clientDetails.getClientSecret(),
				clientDetails.getScope(),
				clientDetails.getResourceIds(),
				clientDetails.getAuthorizedGrantTypes(),
				clientDetails.getRegisteredRedirectUri(),
				new ArrayList(clientDetails.getAuthorities()),
				clientDetails.getAccessTokenValiditySeconds(),
				clientDetails.getRefreshTokenValiditySeconds(),
				clientDetails.getAdditionalInformation(),
				getAutoApproveScopes(clientDetails));
		final boolean result = mongoClientDetailsRepository.update(mongoClientDetails);

		if (!result) {
			throw new NoSuchClientException("No such Client Id");
		}
	}

	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		final boolean result = mongoClientDetailsRepository.updateClientSecret(clientId, passwordEncoder.encode(secret));
		if (!result) {
			throw new NoSuchClientException("No such client id");
		}
	}

	@Override
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		final boolean result = mongoClientDetailsRepository.deleteByClientId(clientId);
		if (!result) {
			throw new NoSuchClientException("No such client id");
		}
	}

	@Override
	public List<ClientDetails> listClientDetails() {

		return mongoClientDetailsRepository
				.findAll()
				.stream()
				.map(mongoClientDetails -> new BaseClientDetails(mongoClientDetails.getClientId(),
						mongoClientDetails.getResourceIds().stream().collect(Collectors.joining(",")),
						mongoClientDetails.getScope().stream().collect(Collectors.joining(",")),
						mongoClientDetails.getAuthorizedGrantTypes().stream().collect(Collectors.joining(",")),
						mongoClientDetails.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.joining(",")),
						mongoClientDetails.getRegisteredRedirectUri().stream().collect(Collectors.joining(","))))
				.collect(Collectors.toList());

	}

	private Set<String> getAutoApproveScopes(final ClientDetails clientDetails) {
		if (clientDetails.isAutoApprove("true")) {
			Set<String> set = new HashSet<>();
			set.add("true");
			return set; // all scopes autoapproved
		}
		return clientDetails.getScope().stream().filter(ByAutoApproveOfScope(clientDetails)).collect(Collectors.toSet());
	}

	private Predicate<String> ByAutoApproveOfScope(final ClientDetails clientDetails) {
		return new Predicate<String>() {
			@Override
			public boolean test(final String scope) {
				return  clientDetails.isAutoApprove(scope);
			}
		};
	}
}
