package com.szb.mongo.oauth;

import com.szb.mongo.oauth.domain.MongoApproval;
import com.szb.mongo.oauth.repositories.MongoApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MongoApprovalStore implements ApprovalStore {

    private final MongoApprovalRepository mongoApprovalRepository;

    private boolean handleRevocationsAsExpiry = false;

    @Autowired
    public MongoApprovalStore(final MongoApprovalRepository mongoApprovalRepository) {
        this.mongoApprovalRepository = mongoApprovalRepository;
    }

    @Override
    public boolean addApprovals(final Collection<Approval> approvals) {
        final Collection<MongoApproval> mongoApprovals =
                approvals.stream().map(toMongoApproval()).collect(Collectors.toList());

        return mongoApprovalRepository.updateOrCreate(mongoApprovals);
    }

    @Override
    public boolean revokeApprovals(final Collection<Approval> approvals) {
        boolean success = true;

        final Collection<MongoApproval> mongoApprovals =
                approvals.stream().map(toMongoApproval()).collect(Collectors.toList());

        for (final MongoApproval mongoApproval : mongoApprovals) {
            if (handleRevocationsAsExpiry) {
                final boolean updateResult = mongoApprovalRepository.updateExpiresAt(new Date(), mongoApproval);
                if (!updateResult) {
                    success = false;
                }

            }
            else {
                final boolean deleteResult = mongoApprovalRepository.deleteByUserIdAndClientIdAndScope(mongoApproval);

                if (!deleteResult) {
                    success = false;
                }
            }
        }
        return success;
    }

    @Override
    public Collection<Approval> getApprovals(final String userId,
                                             final String clientId) {
        final List<MongoApproval> mongoApprovals = mongoApprovalRepository.findByUserIdAndClientId(userId, clientId);
        return mongoApprovals.stream().map(toApproval()).collect(Collectors.toList());
    }

    private Function<Approval, MongoApproval> toMongoApproval() {
        return new Function<Approval, MongoApproval>() {
            @Override
            public MongoApproval apply(final Approval approval) {
                return new MongoApproval(UUID.randomUUID().toString(),
                        approval.getUserId(),
                        approval.getClientId(),
                        approval.getScope(),
                        approval.getStatus() == null ? Approval.ApprovalStatus.APPROVED: approval.getStatus(),
                        approval.getExpiresAt(),
                        approval.getLastUpdatedAt());
            }
        };
    }

    private Function<MongoApproval, Approval> toApproval() {
        return new Function<MongoApproval, Approval>() {
            @Override
            public Approval apply(final MongoApproval mongoApproval) {
                return new Approval(mongoApproval.getUserId(),
                        mongoApproval.getClientId(),
                        mongoApproval.getScope(),
                        mongoApproval.getExpiresAt(),
                        mongoApproval.getStatus(),
                        mongoApproval.getLastUpdatedAt());
            }
        };
    }

    public void setHandleRevocationsAsExpiry(boolean handleRevocationsAsExpiry) {
        this.handleRevocationsAsExpiry = handleRevocationsAsExpiry;
    }
}
