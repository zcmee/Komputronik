package com.github.zcmee.komputronik.listeners;

import com.github.zcmee.komputronik.entities.AuditedRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditingRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        auditedRevisionEntity.setLogin(userName);
    }
}
