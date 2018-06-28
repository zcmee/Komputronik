package com.github.zcmee.komputronik.entities;


import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import com.github.zcmee.komputronik.listeners.AuditingRevisionListener;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@RevisionEntity(AuditingRevisionListener.class)
public class AuditedRevisionEntity extends DefaultRevisionEntity {
    private static final long serialVersionUID = 1L;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditedRevisionEntity)) return false;
        if (!super.equals(o)) return false;
        AuditedRevisionEntity that = (AuditedRevisionEntity) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId);
    }
}