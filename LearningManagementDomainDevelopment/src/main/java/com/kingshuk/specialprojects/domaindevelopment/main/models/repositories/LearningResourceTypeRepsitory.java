package com.kingshuk.specialprojects.domaindevelopment.main.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingshuk.specialprojects.domaindevelopment.main.models.resource.LearningResourceCategory;

public interface LearningResourceTypeRepsitory extends JpaRepository<LearningResourceCategory, String> {

}
