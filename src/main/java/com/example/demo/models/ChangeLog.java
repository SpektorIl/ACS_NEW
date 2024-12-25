package com.example.demo.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@JsonSerialize
@Entity
@Table(name = "change_log")
public class ChangeLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String changeType;

    private String entityClass;

    private Long entityId;

    private String changeDetails;

    private LocalDateTime changeTimestamp = LocalDateTime.now();

    public ChangeLog(String changeType, String entityClass, Long entityId, String changeDetails) {
        this.changeType = changeType;
        this.entityClass = entityClass;
        this.entityId = entityId;
        this.changeDetails = changeDetails;
    }

    public ChangeLog() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getChangeDetails() {
        return changeDetails;
    }

    public void setChangeDetails(String changeDetails) {
        this.changeDetails = changeDetails;
    }

    public LocalDateTime getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(LocalDateTime changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }
}

