package com.github.zcmee.komputronik.dtos;

import com.github.zcmee.komputronik.components.CompLeadAddMapper;
import com.github.zcmee.komputronik.entities.CompLead;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompLeadAddDTOTest {
    
    @Autowired
    private CompLeadAddMapper compLeadAddMapper;    //powinien być final ??
    
    @Test
    public void convertLeadCompEntityToLeadCompDTOTest() {
        CompLead compLead = new CompLead();
        compLead.setCompanyName("Januszex");
        compLead.setContactPerson("Pablo Escobar");
        compLead.setContactPersonPhone("123456789");
        compLead.setNip("1859862233");
        
        CompLeadAddDTO compLeadAddDTO = compLeadAddMapper.map(compLead);
        
        validData(compLead,compLeadAddDTO);
    }
    
    @Test
    public void convertLeadCompDTOToCompLeadEntityTest() {
        CompLeadAddDTO compLeadAddDTO = new CompLeadAddDTO();
        compLeadAddDTO.setCompanyName("Januszex");
        compLeadAddDTO.setFirstName("Pablo");
        compLeadAddDTO.setLastName("Escobar");
        compLeadAddDTO.setPhone("123456789");
        compLeadAddDTO.setNip("1859862233");
        
        CompLead compLead = compLeadAddMapper.map(compLeadAddDTO);
        
        validData(compLead,compLeadAddDTO);
    }
    
    private void validData(CompLead compLead, CompLeadAddDTO compLeadAddDTO) {
        assertEquals(compLead.getCompanyName(), compLeadAddDTO.getCompanyName());
        String fullName = compLeadAddDTO.getFirstName() + " " + compLeadAddDTO.getLastName();
        assertEquals(compLead.getContactPerson(), fullName);
        assertEquals(compLead.getContactPersonPhone(), compLeadAddDTO.getPhone());
        assertEquals(compLead.getNip(), compLeadAddDTO.getNip());
    }
    
}