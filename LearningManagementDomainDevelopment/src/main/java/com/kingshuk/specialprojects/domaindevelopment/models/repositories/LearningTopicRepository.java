package com.kingshuk.specialprojects.domaindevelopment.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingshuk.specialprojects.domaindevelopment.models.LearningTopic;

public interface LearningTopicRepository extends PagingAndSortingRepository<LearningTopic, String> {

}
