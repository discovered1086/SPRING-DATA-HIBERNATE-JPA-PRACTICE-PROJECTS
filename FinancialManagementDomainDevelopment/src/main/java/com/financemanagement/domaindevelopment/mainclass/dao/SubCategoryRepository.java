package com.financemanagement.domaindevelopment.mainclass.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.domaindevelopment.mainclass.model.newmodels.SubCategoryEntity;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, String> {

}
