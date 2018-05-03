package com.github.zcmee.komputronik.services;

import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;
import com.github.zcmee.komputronik.entities.CompLead;

import java.util.Collection;
import java.util.List;

public interface CustomInterfaceCompLead {
    List<CompLead> findAllByRecommendationStatus(RecommendationStatus recommendationStatus);
    List<CompLead> findAllByRecommendationStatuses(Collection<RecommendationStatus> recommendationStatuses);
}
