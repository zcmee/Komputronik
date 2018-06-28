package com.github.zcmee.komputronik.dtos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class CompLeadAddDTO implements Serializable {

    @NotEmpty(message = "Pole mię jest polem obowiązkowym")
    private String contactPerson;

    @NotEmpty(message = "Numer telefonu jest polem obowiązkowym")
    @Pattern(regexp = "[1-9][0-9]{8}", message = "Podany numer telefonu jest nieprawidłowy")
    private String contactPersonPhone;

    @NotEmpty(message = "Nazwa firmy jest polem obowiązkowym")
    private String companyName;

    @NotEmpty(message = "Nip jest polem obowiązkowym")
    private String nip;

    public CompLeadAddDTO() {
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public String toString() {
        return "CompLeadAddDTO{" +
                "contactPerson='" + contactPerson + '\'' +
                ", contactPersonPhone='" + contactPersonPhone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                '}';
    }

}
