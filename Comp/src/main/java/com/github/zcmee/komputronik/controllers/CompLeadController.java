package com.github.zcmee.komputronik.controllers;

import com.github.zcmee.komputronik.AuthorizationUser;
import com.github.zcmee.komputronik.dictionaries.ExceptedService;
import com.github.zcmee.komputronik.dictionaries.OrderStatus;
import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;
import com.github.zcmee.komputronik.dtos.CompLeadAddDTO;
import com.github.zcmee.komputronik.dtos.CompLeadOplDTO;
import com.github.zcmee.komputronik.dtos.CompLeadOrDTO;
import com.github.zcmee.komputronik.entities.CompLead;
import com.github.zcmee.komputronik.entities.User;
import com.github.zcmee.komputronik.services.CompLeadService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("compleads")
public class CompLeadController {
    private final ModelMapper modelMapper;
    private final CompLeadService compLeadService;

    public CompLeadController(ModelMapper modelMapper, CompLeadService compLeadService) {
        this.modelMapper = modelMapper;
        this.compLeadService = compLeadService;
    }

    public List<CompLead> findAll() {
        return compLeadService.findAll();
    }

    @GetMapping("reccomendationstatus/{id}")
    public List<CompLead> findAllByReccomendationStatus(@PathVariable Integer id) {
        RecommendationStatus recommendationStatus = RecommendationStatus.valueOf(id);
        return compLeadService.findAllByRecommendationStatus(recommendationStatus);
    }

    @GetMapping("reccomendationstatus/active")
    public List<CompLead> findActiveAllByReccomendation() {
        return compLeadService.findAllByRecommendationStatuses(RecommendationStatus.ACTIVE_STATUSES);
    }

    @GetMapping("{id}")
    public CompLead findById(@PathVariable Long id) {
        return compLeadService.findById(id);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@ModelAttribute CompLeadAddDTO compLeadAddDTO,
                    @AuthenticationPrincipal AuthorizationUser authenticatedUser) {
        CompLead compLead = modelMapper.map(compLeadAddDTO, CompLead.class);

        compLead.setUser(authenticatedUser.getUser());
        compLead.setOrderStatus(OrderStatus.NEW);
        compLead.setRecommendationStatus(RecommendationStatus.ACCEPTED_BY_CLIENT);
        compLead.setExpectedService(ExceptedService.PAID_SERVICE);
        compLeadService.save(compLead);
    }

    @PostMapping("update/opl")
    public CompLead updateOpl(@Valid @ModelAttribute CompLeadOplDTO compLeadOplDTO) {
        CompLead compLead = compLeadService.findById(compLeadOplDTO.getId());
        compLead.updateLead(compLeadOplDTO);
        return compLeadService.save(compLead);
    }

    @PutMapping("update/or")
    public CompLead update(@Valid @ModelAttribute CompLeadOrDTO compLeadOrDTO) {
        CompLead compLead = compLeadService.findById(compLeadOrDTO.getId());
        compLead.updateLead(compLeadOrDTO);
        return compLeadService.save(compLead);
    }

}
