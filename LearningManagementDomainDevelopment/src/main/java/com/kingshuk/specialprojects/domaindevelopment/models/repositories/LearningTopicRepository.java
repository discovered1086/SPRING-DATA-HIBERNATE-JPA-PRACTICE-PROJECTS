package com.kingshuk.specialprojects.domaindevelopment.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingshuk.specialprojects.domaindevelopment.models.topic.LearningTopic;

public interface LearningTopicRepository extends PagingAndSortingRepository<LearningTopic, String> {

}
