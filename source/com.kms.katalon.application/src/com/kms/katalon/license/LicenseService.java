package com.kms.katalon.license;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;

import com.kms.katalon.crypto.LicenseHelper;
import com.kms.katalon.license.models.Feature;
import com.kms.katalon.license.models.License;
import com.auth0.jwt.interfaces.Claim;

import com.kms.katalon.license.constants.LicenseConstants;

public class LicenseService {
    public static LicenseService serviceInstance;
    
    private LicenseService() { }
    
    public static LicenseService getInstance() {
        if (serviceInstance == null) {
            serviceInstance = new LicenseService();
        }
        return serviceInstance;
    }
    
    public License parseJws(String jws) throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        LicenseHelper licenseHelper = LicenseHelper.getInstance();
        Map<String, Claim> claims = licenseHelper.parseJws(jws);
        return getLicenseFromClaims(claims, jws);
    }
    
    public License parseJwsFromFile(String filename) throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        String jws = new String(Files.readAllBytes(Paths.get(filename)));
        return parseJws(jws);
    }
    
    private License getLicenseFromClaims(Map<String, Claim> claims, String jws) throws IOException {
        License license = new License();
        
        long renewTime = claims.get(LicenseConstants.RENEW_TIME).asLong();
        license.setRenewTime(new Date(renewTime));
        
        long expireDate = claims.get(LicenseConstants.EXPIRATION_DATE).asLong();
        license.setExpirationDate(new Date(expireDate));
        
        license.setMachineId(claims.get(LicenseConstants.MACHINE_ID).asString());
        Claim orgId =  claims.get(LicenseConstants.ORGANIZATION_ID);
        if (orgId != null) {
            license.setOrganizationId(claims.get(LicenseConstants.ORGANIZATION_ID).asLong());
        }
        license.setFeatures(claims.get(LicenseConstants.FEATURES).asList(Feature.class));
        license.setJwtCode(jws);
        return license;
    }
}
