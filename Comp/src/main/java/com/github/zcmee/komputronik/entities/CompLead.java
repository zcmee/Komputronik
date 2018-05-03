package com.github.zcmee.komputronik.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.zcmee.komputronik.dtos.CompLeadOplDTO;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import com.github.zcmee.komputronik.converters.CompLeadConverter;
import com.github.zcmee.komputronik.converters.ExceptedServiceConverter;
import com.github.zcmee.komputronik.converters.OrderStatusConverter;
import com.github.zcmee.komputronik.dictionaries.ExceptedService;
import com.github.zcmee.komputronik.dictionaries.OrderStatus;
import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;
import com.github.zcmee.komputronik.dtos.CompLeadOrDTO;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Audited
@JsonInclude(JsonInclude.Include.ALWAYS)
@Table(name = "LEADY_COMP")
@NamedQueries({
    @NamedQuery(name = "CompLead.findAllByRecommendationStatuses", query = "FROM CompLead cl where cl.recommendationStatus IN (:recommendationStatuses) ORDER BY cl.id")
})

public class CompLead implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LC_ID")
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="LC_USER_ID")
    private User user;

    @Convert(converter = ExceptedServiceConverter.class)
    @Column(name = "LC_EXPECTED_SERVICE_ID")
    private ExceptedService expectedService;

    @Convert(converter = CompLeadConverter.class)
    @Column(name = "LC_RECOMMENDATION_STATUS_ID")
    private RecommendationStatus recommendationStatus;

    @Convert(converter = OrderStatusConverter.class)
    @Column(name = "LC_ORDER_STATUS_ID")
    private OrderStatus orderStatus;
    
    @Size(max = 15)
    @Column(name = "LC_TELL_NUMBER", length = 15)
    private String idTel;
    
    @Column(name = "LC_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Size(max = 20)
    @Column(name = "LC_NIP")
    private String nip;
    
    @Size(max = 200)
    @Column(name = "LC_COMPANY_NAME", length = 200)
    private String companyName;
    
    @Size(max = 200)
    @Column(name = "LC_CONTACT_PERSON", length = 200)
    private String contactPerson;
    
    @Size(max = 9)
    @Column(name = "LC_CONTACT_PERSON_PHONE", length = 9)
    private String contactPersonPhone;

    @Column(name = "LC_EXPECTED_INSTALLATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expectedInstallationDate;

    public CompLead() {
        // default constructor
    }

    public void updateLead(CompLeadOplDTO compLeadOplDTO) {
        this.orderStatus = compLeadOplDTO.getOrderStatus();
        this.idTel = compLeadOplDTO.getIdTel();
    }

    public void updateLead(CompLeadOrDTO compLeadOrDTO) {
        this.expectedService = compLeadOrDTO.getExpectedService();
        this.recommendationStatus = compLeadOrDTO.getRecommendationStatus();
        this.expectedInstallationDate = compLeadOrDTO.getExpectedInstallationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExceptedService getExpectedService() {
        return expectedService;
    }

    public void setExpectedService(ExceptedService expectedService) {
        this.expectedService = expectedService;
    }

    public RecommendationStatus getRecommendationStatus() {
        return recommendationStatus;
    }

    public void setRecommendationStatus(RecommendationStatus recommendationStatus) {
        this.recommendationStatus = recommendationStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getIdTel() {
        return idTel;
    }

    public void setIdTel(String idTel) {
        this.idTel = idTel;
    }

    public Date getExpectedInstallationDate() {
        return expectedInstallationDate;
    }

    public void setExpectedInstallationDate(Date expectedInstallationDate) {
        this.expectedInstallationDate = expectedInstallationDate;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}