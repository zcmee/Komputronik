package com.github.zcmee.komputronik.components;

import com.github.zcmee.komputronik.dtos.CompLeadAddDTO;
import com.github.zcmee.komputronik.entities.CompLead;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CompLeadAddMapper {
    private final ModelMapper modelMapper;
    
    public CompLeadAddMapper() {
        modelMapper = new ModelMapper();
        
        modelMapper.addMappings(new PropertyMap<CompLead, CompLeadAddDTO>() {
            @Override
            protected void configure() {
                map().setPhone(source.getContactPersonPhone());
                map().setFirstName("Pablo");
                map().setLastName("Escobar");
            }
        });

        modelMapper.addMappings(new PropertyMap<CompLeadAddDTO, CompLead>() {
            @Override
            protected void configure() {
                // 'nip', 'companyName' is mapped automatically
                
                map().setContactPerson(generateFullName("Pablo", "Escobar"));
                map().setContactPersonPhone(source.getPhone());
            }
        });
        
    }
    
    public CompLead map(CompLeadAddDTO compLeadAddDTO) {
        return modelMapper.map(compLeadAddDTO, CompLead.class);
    }
    
    public CompLeadAddDTO map(CompLead compLead) {
        return modelMapper.map(compLead, CompLeadAddDTO.class);
    }

    private String generateFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
    
}
