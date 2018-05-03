package com.github.zcmee.komputronik.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;
import com.github.zcmee.komputronik.entities.CompLead;
import com.github.zcmee.komputronik.repositories.CompLeadRepository;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CompLeadService implements ServiceInterface<CompLead>, CustomInterfaceCompLead {
    private final CompLeadRepository compLeadRepository;

    public CompLeadService(CompLeadRepository compLeadRepository) {
        this.compLeadRepository = compLeadRepository;
    }

    @Override
    public List<CompLead> findAll() {
        return compLeadRepository.findAll();
    }

    @Override
    public CompLead findById(Long id) {
        return compLeadRepository.findOne(id);
    }

    @Override
    public CompLead save(CompLead compLead) {
        return compLeadRepository.save(compLead);
    }

    @Override
    public List<CompLead> findAllByRecommendationStatus(RecommendationStatus recommendationStatus) {
        return compLeadRepository.findAllByRecommendationStatus(recommendationStatus);
    }

    @Override
    public List<CompLead> findAllByRecommendationStatuses(Collection<RecommendationStatus> recommendationStatuses) {
        return compLeadRepository.findAllByRecommendationStatuses(recommendationStatuses);
    }

}
