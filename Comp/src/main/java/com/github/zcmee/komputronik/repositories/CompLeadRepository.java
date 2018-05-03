package com.github.zcmee.komputronik.repositories;

import com.github.zcmee.komputronik.entities.CompLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;

import java.util.Collection;
import java.util.List;

@Repository
public interface CompLeadRepository extends JpaRepository<CompLead, Long>,
                                            RevisionRepository<CompLead, Long, Integer> {

    List<CompLead> findAllByRecommendationStatus(RecommendationStatus recommendationStatus);

    @Query
    List<CompLead> findAllByRecommendationStatuses(@Param("recommendationStatuses") Collection<RecommendationStatus> recommendationStatuses);
}
