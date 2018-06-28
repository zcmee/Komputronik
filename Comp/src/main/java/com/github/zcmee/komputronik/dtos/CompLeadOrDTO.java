package com.github.zcmee.komputronik.dtos;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.github.zcmee.komputronik.dictionaries.ExceptedService;
import com.github.zcmee.komputronik.dictionaries.RecommendationStatus;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class CompLeadOrDTO implements Serializable {
    @NotNull(message = "Pole id polecenia jest polem obowiązkowym")
    private Long id;

    @NotNull(message = "Pole oczekiwana usługa jest polem obowiązkowym")
    private Integer expectedService;

    @NotNull(message = "Pole status jest polem obowiązkowym")
    private Integer recommendationStatus;

    @Temporal(TemporalType.DATE)
    private Date expectedInstallationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExceptedService getExpectedService() {
        return ExceptedService.valueOf(expectedService);
    }

    public void setExpectedService(Integer expectedService) {
        this.expectedService = expectedService;
    }

    public RecommendationStatus getRecommendationStatus() {
        return RecommendationStatus.valueOf(recommendationStatus);
    }

    public void setRecommendationStatus(Integer recommendationStatus) { this.recommendationStatus = recommendationStatus; }

    public Date getExpectedInstallationDate() {
        return expectedInstallationDate;
    }

    public void setExpectedInstallationDate(String expectedInstallationDate) {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime convertedExpectedInstallationDate = dateFormatter.parseDateTime(expectedInstallationDate);
        this.expectedInstallationDate = convertedExpectedInstallationDate.toDate();
    }

}
