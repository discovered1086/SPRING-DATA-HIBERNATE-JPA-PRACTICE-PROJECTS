package com.kingshuk.specialprojects.domaindevelopment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.kingshuk.specialprojects.domaindevelopment.models.repositories.LearningResourceRepository;
import com.kingshuk.specialprojects.domaindevelopment.models.repositories.LearningResourceTypeRepsitory;
import com.kingshuk.specialprojects.domaindevelopment.models.repositories.LearningTopicRepository;
import com.kingshuk.specialprojects.domaindevelopment.models.repositories.LearningTopicTypeRepository;
import com.kingshuk.specialprojects.domaindevelopment.models.repositories.LearningTrackRepository;

@SpringBootApplication
public class LearningManagementDomainDevelopmentApplication {
	
	@Autowired
	private LearningTrackRepository trackRepository;
	
	@Autowired
	private LearningTopicRepository topicRepository;
	
	@Autowired
	private LearningResourceRepository resourceRepository;
	
	@Autowired
	private LearningTopicTypeRepository topicTypeRepository;
	
	@Autowired
	private LearningResourceTypeRepsitory resourceTypeRepsitory;

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementDomainDevelopmentApplication.class, args);
		
		LearningManagementDomainDevelopmentApplication application 
		= new LearningManagementDomainDevelopmentApplication();
		
		application.performDatabaseWork();
	}
	
	@Transactional
	public void performDatabaseWork() {
		
	}

}
