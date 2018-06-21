package com.github.zcmee.komputronik.dtos;

import org.hibernate.validator.constraints.NotEmpty;

public class CompLeadAddDTO {

    @NotEmpty(message = "Pole mię jest polem obowiązkowym")
    private String firstName;

    @NotEmpty(message = "Pole nazwisko jest polem obowiązkowym")
    private String lastName;

    @NotEmpty(message = "Numer telefonu jest polem obowiązkowym")
    private String phone;

    @NotEmpty(message = "Nazwa firmy jest polem obowiązkowym")
    private String companyName;

    @NotEmpty(message = "Nip jest polem obowiązkowym")
    private String nip;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                '}';
    }
}
