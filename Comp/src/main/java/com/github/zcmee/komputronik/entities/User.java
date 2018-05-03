package com.github.zcmee.komputronik.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.zcmee.komputronik.dictionaries.AccountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"US_LOGIN", "US_TELEFON", "US_EMAIL"})})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "US_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Login jest polem obowiązkowym")
    @Size(min = 3, max = 100, message = "Login musi zawierać się pomiędzy 3-10 znaków")
    @Column(name = "US_LOGIN", nullable = false, length = 100)
    private String login;

    @Size(max = 100)
    @Column(name = "US_IMIE", length = 100)
    private String firstName;

    @Size(max = 100)
    @Column(name = "US_NAZWISKO", length = 100)
    private String lastName;

    @JsonIgnore
    @Size(max = 300)
    @Column(name = "US_PASSWD", length = 300)
    private String password;

    @Column(name = "US_TELEFON")
    @Pattern(message = "Numer telefonu musi być w formacie XXXXXXXXX gdzie X jest cyfrą", regexp = "^[0-9]{9}$")
    private String phone;

    @Pattern(message = "Podany adres email jest niepoprawny", regexp = "^[-a-z0-9~!$%^&*_=+}{'?]+(.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?")
    @Size(min = 3, max = 50, message = "Adres Email musi zawierać się pomiędzy 3-50 znakami")
    @Column(name = "US_EMAIL", length = 50)
    private String email;

    @Size(max = 100)
    @Column(name = "US_MIASTO", length = 100)
    private String city;

    @Size(max = 300)
    @Column(name = "US_ULICA", length = 300)
    private String street;

    @Size(max = 100)
    @Column(name = "US_BUDYNEK", length = 100)
    private String building;

    @Size(max = 100)
    @Column(name = "US_MIESZKANIE", length = 100)
    private String flat;

    @Size(max = 15)
    @Column(name = "US_KOD_POCZTOWY", length = 15)
    private String zipCode;

    @JsonIgnore
    @Column(name = "US_RODZAJ_KONTA", nullable = false)
    private Integer accountType;

    @JsonIgnore
    @Column(name = "US_STATUS_KONTA")
    private Integer accountStatus;

    public User() {
        // default constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public AccountType getAccountType() {
        return AccountType.valueOf(accountType);
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", flat='" + flat + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", accountType=" + accountType +
                ", accountStatus=" + accountStatus +
                '}';
    }
}