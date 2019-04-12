package com.kingshuk.specialprojects.domaindevelopment.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingshuk.specialprojects.domaindevelopment.models.LearningResource;

public interface LearningResourceRepository extends PagingAndSortingRepository<LearningResource, String> {

}
