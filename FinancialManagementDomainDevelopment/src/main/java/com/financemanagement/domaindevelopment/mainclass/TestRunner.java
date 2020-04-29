package com.financemanagement.domaindevelopment.mainclass;

import java.io.File;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financemanagement.domaindevelopment.allmodels.model.newmodels.CategoryEntity;
import com.financemanagement.domaindevelopment.dao.CategoryRepository;
import com.financemanagement.domaindevelopment.dao.SubCategoryRepository;

@Component
@Profile("generalRunner")
public class TestRunner implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public void run(String... args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		CategoryEntity categoryEntity = mapper.readValue(new File("jsonfiles/category.json"), CategoryEntity.class);

		if (Objects.nonNull(categoryEntity.getSubCategoryEntities())
				&& !categoryEntity.getSubCategoryEntities().isEmpty()) {
			categoryEntity.getSubCategoryEntities()
					.forEach(subCategoryEntity -> subCategoryRepository.save(subCategoryEntity));
		}

		categoryRepository.save(categoryEntity);
	}

}
