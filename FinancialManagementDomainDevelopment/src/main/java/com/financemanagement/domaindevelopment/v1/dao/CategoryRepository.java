package com.financemanagement.domaindevelopment.v1.dao;

import com.financemanagement.domaindevelopment.compositekeymapping.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
