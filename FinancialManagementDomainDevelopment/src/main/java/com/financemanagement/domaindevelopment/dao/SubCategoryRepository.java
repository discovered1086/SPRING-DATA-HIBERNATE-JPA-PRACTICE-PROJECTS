package com.financemanagement.domaindevelopment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.domaindevelopment.allmodels.model.newmodels.SubCategoryEntity;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, String> {

}
