package com.kingshuk.specialprojects.domaindevelopment.main.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingshuk.specialprojects.domaindevelopment.main.models.resource.LearningResource;

public interface LearningResourceRepository extends PagingAndSortingRepository<LearningResource, String> {

}
