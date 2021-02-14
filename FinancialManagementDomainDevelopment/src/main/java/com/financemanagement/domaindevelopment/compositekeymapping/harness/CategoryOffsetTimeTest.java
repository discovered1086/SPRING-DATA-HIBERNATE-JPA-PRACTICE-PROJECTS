package com.financemanagement.domaindevelopment.compositekeymapping.harness;

import com.financemanagement.domaindevelopment.compositekeymapping.models.CategoryEntity;
import com.financemanagement.domaindevelopment.compositekeymapping.models.SubCategoryEntity;
import com.financemanagement.domaindevelopment.v1.dao.CategoryRepository;
import com.financemanagement.domaindevelopment.v1.dao.SubCategoryRepository;
import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Component
@Profile("categoryOffsetTest")
public class CategoryOffsetTimeTest implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public void run(String... args) throws Exception {
		// OffsetDateTime effectiveDate =
		// OffsetDateTime.parse("2019-11-25T00:00:00+05:30");

		// OffsetDateTime terminationDate =
		// OffsetDateTime.parse("2020-11-25T00:00:00+05:30");

		ZonedDateTime effectiveDate = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS);

		ZonedDateTime terminationDate = ZonedDateTime.now().plusYears(1L).truncatedTo(ChronoUnit.SECONDS);

		SubCategoryEntity subCategoryReferenceEntity = SubCategoryEntity.builder()
				.subCategoryDescription("Credit card bill payment").subCategoryName("Credit card bill payment")
				.subCategoryEffectiveDate(effectiveDate).subCategoryTerminationDate(terminationDate).build();

		Set<SubCategoryEntity> subCategoryEntities = new HashSet<>();
		subCategoryEntities.add(subCategoryReferenceEntity);

		CategoryEntity categoryEntity = CategoryEntity.builder()
				.categoryDescription("Bill Payment for various types of bills").categoryName("Bill Payment")
				.categoryEffectiveDate(effectiveDate).categoryTransactionType(TransactionTypeEnum.EXPENSE)
				.subCategoryEntities(subCategoryEntities).categoryTerminationDate(terminationDate).build();

		if (Objects.nonNull(categoryEntity.getSubCategoryEntities())
				&& !categoryEntity.getSubCategoryEntities().isEmpty()) {
			categoryEntity.getSubCategoryEntities()
					.forEach(subCategoryEntity -> subCategoryRepository.save(subCategoryEntity));
		}

		CategoryEntity savedEntity = categoryRepository.save(categoryEntity);

		System.out.println(categoryRepository.getOne(savedEntity.getCategoryId()).getCategoryEffectiveDate());
	}

}
