package com.kingshuk.specialprojects.domaindevelopment.main.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingshuk.specialprojects.domaindevelopment.main.models.learningtrack.LearningTrack;

public interface LearningTrackRepository extends PagingAndSortingRepository<LearningTrack, String> {

}
