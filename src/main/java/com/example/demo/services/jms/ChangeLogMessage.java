package com.example.demo.services.jms;

public class ChangeLogMessage {
    private String changeType;
    private String entityClass;
    private Long entityId;
    private String details;

    public ChangeLogMessage(String changeType, String entityClass, Long entityId, String details) {
        this.changeType = changeType;
        this.entityClass = entityClass;
        this.entityId = entityId;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
