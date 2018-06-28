package com.github.zcmee.komputronik.listeners;

import com.github.zcmee.komputronik.AuthorizationUser;
import com.github.zcmee.komputronik.entities.AuditedRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditingRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;
        AuthorizationUser authorizationUser = (AuthorizationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        auditedRevisionEntity.setUserId(authorizationUser.getUser().getId());
    }

}
