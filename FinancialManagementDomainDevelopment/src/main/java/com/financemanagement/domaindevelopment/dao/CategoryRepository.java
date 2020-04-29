package com.financemanagement.domaindevelopment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.domaindevelopment.allmodels.model.newmodels.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
