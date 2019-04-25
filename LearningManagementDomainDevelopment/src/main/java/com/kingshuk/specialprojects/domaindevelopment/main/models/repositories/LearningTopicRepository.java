package com.kingshuk.specialprojects.domaindevelopment.main.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingshuk.specialprojects.domaindevelopment.main.models.topic.LearningTopic;

public interface LearningTopicRepository extends PagingAndSortingRepository<LearningTopic, String> {

}
