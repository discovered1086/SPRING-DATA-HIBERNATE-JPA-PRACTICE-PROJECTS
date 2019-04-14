package com.kingshuk.specialprojects.domaindevelopment.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingshuk.specialprojects.domaindevelopment.models.resource.LearningResourceType;

public interface LearningResourceTypeRepsitory extends JpaRepository<LearningResourceType, String> {

}
