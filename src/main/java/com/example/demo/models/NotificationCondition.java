package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_conditions")
public class NotificationCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "attribute_condition")
    private String attributeCondition;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getAttributeCondition() {
        return attributeCondition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setAttributeCondition(String attributeCondition) {
        this.attributeCondition = attributeCondition;
    }


}
