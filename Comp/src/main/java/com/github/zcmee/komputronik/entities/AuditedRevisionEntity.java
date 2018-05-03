package com.github.zcmee.komputronik.entities;


import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import com.github.zcmee.komputronik.listeners.AuditingRevisionListener;

import javax.persistence.Entity;

@Entity
@RevisionEntity(AuditingRevisionListener.class)
public class AuditedRevisionEntity extends DefaultRevisionEntity {
    private static final long serialVersionUID = 1L;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}