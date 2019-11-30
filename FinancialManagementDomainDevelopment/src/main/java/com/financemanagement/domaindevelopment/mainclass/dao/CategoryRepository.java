package com.financemanagement.domaindevelopment.mainclass.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.domaindevelopment.mainclass.model.newmodels.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
