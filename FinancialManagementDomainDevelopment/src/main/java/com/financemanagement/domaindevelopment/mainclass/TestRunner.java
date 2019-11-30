package com.financemanagement.domaindevelopment.mainclass;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financemanagement.domaindevelopment.mainclass.dao.CategoryRepository;
import com.financemanagement.domaindevelopment.mainclass.dao.SubCategoryRepository;
import com.financemanagement.domaindevelopment.mainclass.model.newmodels.CategoryEntity;

@Component
public class TestRunner implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public void run(String... args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		CategoryEntity categoryEntity = mapper.readValue(new File("jsonfiles/category.json"), CategoryEntity.class);

		categoryRepository.save(categoryEntity);
	}

}
