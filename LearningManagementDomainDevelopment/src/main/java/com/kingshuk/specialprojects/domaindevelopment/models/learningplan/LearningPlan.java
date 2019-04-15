package com.kingshuk.specialprojects.domaindevelopment.models.learningplan;

import java.util.Map;

import com.kingshuk.specialprojects.domaindevelopment.models.LearningItem;

public class LearningPlan {

	private String learningPlanId;
	
	private LearningItem learningItem;
	
	private Map<Integer, LearningPlanItem> learningPlanItems;
}
