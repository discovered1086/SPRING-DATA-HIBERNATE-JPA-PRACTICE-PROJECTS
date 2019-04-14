package com.kingshuk.specialprojects.domaindevelopment.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingshuk.specialprojects.domaindevelopment.models.topic.LearningTopicType;

public interface LearningTopicTypeRepository extends JpaRepository<LearningTopicType, String> {

}
