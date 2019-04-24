package com.kingshuk.specialprojects.domaindevelopment.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingshuk.specialprojects.domaindevelopment.models.topic.LearningTopicCategory;

public interface LearningTopicTypeRepository extends JpaRepository<LearningTopicCategory, String> {

}
