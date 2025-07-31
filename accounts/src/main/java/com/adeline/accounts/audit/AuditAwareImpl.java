package com.adeline.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/// AuditorAware interface so that the auditing infrastructure can tell when
/// to update auditing annotations, like @CreatedBy or @LastModifiedBy
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS"); /// --> hard-coding values until we reach the security section
    }
}
