package com.testexcelreader.excelreader.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class PeopleData {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonDeserialize(using = StringToJsonDeserializer.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long peopleId;
    @JsonAlias({"PID"})
    private String pId;
    @JsonAlias("Project ID")
    private String projectId;
    @JsonAlias({"Resource Name"})
    @NotNull
    private String resourceName;
    @JsonAlias({"Email"})
    @Email
    private String email;
    @JsonAlias({"LDAP"})
    private String ldap;
    @JsonAlias("Resource Status")
    private String resourceStatus;
    @JsonAlias("Laptop Status")
    private String laptopStatus;
    @JsonAlias("Standard Role Category")
    private String deloitteRole;
    @JsonAlias("Role")
    private String googleRole;
    @JsonAlias("Job Function Group (JFG)")
    private String jobFunctionGroup;
    @JsonAlias("Location (City)")
    private String locationCity;
    @JsonAlias("Location (Country)")
    private String locationCountry;
    private String primarySkills;
    private String secondarySkills;
    @JsonAlias("Delivery Start Date")
    private String deliveryStartDate;
    @JsonAlias("Delivery End Date")
    private String deliveryEndDate;

    public PeopleData() {
    }

    public PeopleData(Long peopleId, String pId, String projectId, String resourceName, String email, String ldap, String resourceStatus, String laptopStatus, String deloitteRole, String googleRole, String jobFunctionGroup, String locationCity, String locationCountry, String primarySkills, String secondarySkills, String deliveryStartDate, String deliveryEndDate) {
        this.peopleId = peopleId;
        this.pId = pId;
        this.projectId = projectId;
        this.resourceName = resourceName;
        this.email = email;
        this.ldap = ldap;
        this.resourceStatus = resourceStatus;
        this.laptopStatus = laptopStatus;
        this.deloitteRole = deloitteRole;
        this.googleRole = googleRole;
        this.jobFunctionGroup = jobFunctionGroup;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.primarySkills = primarySkills;
        this.secondarySkills = secondarySkills;
        this.deliveryStartDate = deliveryStartDate;
        this.deliveryEndDate = deliveryEndDate;
    }

    public String getGoogleRole() {
        return googleRole;
    }

    public void setGoogleRole(String googleRole) {
        this.googleRole = googleRole;
    }

    public Long getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Long peopleId) {
        this.peopleId = peopleId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLdap() {
        return ldap;
    }

    public void setLdap(String ldap) {
        this.ldap = ldap;
    }

    public String getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public String getLaptopStatus() {
        return laptopStatus;
    }

    public void setLaptopStatus(String laptopStatus) {
        this.laptopStatus = laptopStatus;
    }

    public String getDeloitteRole() {
        return deloitteRole;
    }

    public void setDeloitteRole(String deloitteRole) {
        this.deloitteRole = deloitteRole;
    }

    public String getJobFunctionGroup() {
        return jobFunctionGroup;
    }

    public void setJobFunctionGroup(String jobFunctionGroup) {
        this.jobFunctionGroup = jobFunctionGroup;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getPrimarySkills() {
        return primarySkills;
    }

    public void setPrimarySkills(String primarySkills) {
        this.primarySkills = primarySkills;
    }

    public String getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(String secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public String getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(String deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public String getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(String deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    @Override
    public String toString() {
        return "PeopleData{" +
                "peopleId=" + peopleId +
                ", pId=" + pId +
                ", projectId='" + projectId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", email='" + email + '\'' +
                ", ldap='" + ldap + '\'' +
                ", resourceStatus='" + resourceStatus + '\'' +
                ", laptopStatus='" + laptopStatus + '\'' +
                ", deloitteRole='" + deloitteRole + '\'' +
                ", googleRole='" + googleRole + '\'' +
                ", jobFunctionGroup='" + jobFunctionGroup + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                ", primarySkills='" + primarySkills + '\'' +
                ", secondarySkills='" + secondarySkills + '\'' +
                ", deliveryStartDate=" + deliveryStartDate +
                ", deliveryEndDate=" + deliveryEndDate +
                '}';
    }
}
