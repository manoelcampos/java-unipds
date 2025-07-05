package com.example.events.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

/**
 * @author Manoel Campos
*/
@Entity
public class Subscription {
    @Id @NotNull
    private SubscriptionID id;

    @NotNull @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    private int level;

    public SubscriptionID getId() {
        return id;
    }

    public void setId(SubscriptionID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
