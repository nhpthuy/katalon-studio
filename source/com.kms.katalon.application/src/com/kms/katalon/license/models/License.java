package com.kms.katalon.license.models;

import java.util.Date;
import java.util.List;

public class License {
    
    private String jwtCode;
    
    private Date expirationDate;
    
    private Date renewTime;

    private String machineId;

    private List<Feature> features;

    private long organizationId;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public Date getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Date renewTime) {
        this.renewTime = renewTime;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getJwtCode() {
        return jwtCode;
    }

    public void setJwtCode(String jwtCode) {
        this.jwtCode = jwtCode;
    }
    
}
