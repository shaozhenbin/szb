package com.szb.mongo.oauth.repositories;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.szb.mongo.oauth.domain.MongoApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class MongoApprovalRepositoryImpl implements MongoApprovalRepositoryBase {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public MongoApprovalRepositoryImpl(final MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public boolean updateOrCreate(final Collection<MongoApproval> mongoApprovals) {
		boolean result = true;
		for (MongoApproval mongoApproval : mongoApprovals) {
			final Update update = Update
					.update("expiresAt", mongoApproval.getExpiresAt())
					.addToSet("status", mongoApproval.getStatus())
					.addToSet("lastModifiedAt",
							mongoApproval.getLastUpdatedAt());

			final UpdateResult writeResult = mongoTemplate.upsert(
					byUserIdAndClientIdAndScope(mongoApproval), update,
					MongoApproval.class);

			if (writeResult.getModifiedCount()!= 1) {
				result = false;
			}
		}
		return result;
	}

	@Override
	public boolean updateExpiresAt(final Date expiresAt,
			final MongoApproval mongoApproval) {
		final Update update = Update.update("expiresAt", expiresAt);

		final UpdateResult writeResult = mongoTemplate.updateFirst(
				byUserIdAndClientIdAndScope(mongoApproval), update,
				MongoApproval.class);

		return writeResult.getModifiedCount() == 1;
	}

	@Override
	public boolean deleteByUserIdAndClientIdAndScope(
			final MongoApproval mongoApproval) {
		final DeleteResult writeResult = mongoTemplate
				.remove(byUserIdAndClientIdAndScope(mongoApproval),
						MongoApproval.class);

		return writeResult.getDeletedCount() == 1;
	}

	@Override
	public List<MongoApproval> findByUserIdAndClientId(final String userId,
			final String clientId) {
		final Query query = Query.query(where("userId").is(userId).andOperator(
				where("clientId").is(clientId)));
		return mongoTemplate.find(query, MongoApproval.class);
	}

	private Query byUserIdAndClientIdAndScope(final MongoApproval mongoApproval) {
		return Query.query(where("userId").is(mongoApproval.getUserId())
				.andOperator(
						where("clientId").is(mongoApproval.getClientId())
								.andOperator(
										where("scope").is(
												mongoApproval.getScope()))));
	}
}
