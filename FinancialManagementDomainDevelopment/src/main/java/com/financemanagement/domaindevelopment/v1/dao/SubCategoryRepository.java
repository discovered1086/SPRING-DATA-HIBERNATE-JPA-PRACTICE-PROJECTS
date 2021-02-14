package com.financemanagement.domaindevelopment.v1.dao;

import com.financemanagement.domaindevelopment.compositekeymapping.models.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, String> {

}
