package com.example.events.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

/**
 * @author Manoel Campos
 */
@Embeddable
public class SubscriptionID {
    @ManyToOne
    private UserAccount user;

    @ManyToOne
    private Session session;

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
