package com.example.events.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

/**
 * @author Manoel Campos
 */
@Embeddable
public class SubscriptionID {
    @ManyToOne
    private UserAccount userAccount;

    @ManyToOne
    private Session session;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
