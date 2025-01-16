package com.example.demo.models.repositories;

import com.example.demo.models.NotificationCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationConditionRepository extends JpaRepository<NotificationCondition, Long> {
    List<NotificationCondition> findAllByEntityNameEquals(String entityName);
}