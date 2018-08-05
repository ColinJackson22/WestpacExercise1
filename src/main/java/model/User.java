package model;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class User {

    private static Category log = Logger.getLogger(User.class);

    private String realMeUsername;
    private String realMePassword;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userType;
    private String email;
    private String organisation;
    private String longName;
    private String phoneCountryCode;
    private String phoneAreaCode;
    private String phoneNumber;
    private String phoneExtension;
    private String mobileCountryCode;
    private String mobileAreaCode;
    private String mobileNumber;
    private Boolean loggedIn;
    private String userCreateStatus;
    private String userUniqueID;
    private Map<String,String> registerProperties;
    private Map<String,Map<String, String>> registerAddressDetails;
    private Map<String,String> partnerDetails;
    private Map<String,String> organisationAddress;
    private List<Map<String,String>> generalPartnerList;
    private List<Map<String,String>> limitedPartnerList;
    private String internalUsername;
    private String internalPassword;
    private LocalDate sysDate;

    public String getRealMeUsername(){ return this.realMeUsername; }
    public void setRealMeUsername(String realMeUsername){ this.realMeUsername = realMeUsername; }

    public String getRealMePassword(){ return this.realMePassword; }
    public void setRealMePassword(String realMePassword){ this.realMePassword = realMePassword; }

    public String getFirstName(){ return this.firstName; }
    public void setFirstName(String firstName){ this.firstName = firstName; }

    public String getMiddleName(){ return this.middleName; }
    public void setMiddleName(String middleName){ this.middleName = middleName; }

    public String getLastName(){ return this.lastName; }
    public void setLastName(String lastName){ this.lastName = lastName; }

    public String getUserType(){ return this.userType; }
    public void setUserType(String userType){ this.userType = userType; }

    public String getUserCreateStatus(){ return this.userCreateStatus; }
    public void setUserCreateStatus(String userCreateStatus){ this.userCreateStatus = userCreateStatus; }

    public String getEmail(){ return this.email; }
    public void setEmail(String email){ this.email = email; }

    public String getOrganisation(){ return this.organisation; }
    public void setOrganisation(String organisation){ this.organisation = organisation; }

    public String getLongName(){ return this.longName; }
    public void setLongName(String longName){ this.longName = longName; }

    public Boolean getLoggedIn() { return this.loggedIn; }
    public void setLoggedIn(Boolean loggedIn){ this.loggedIn = loggedIn; }

    public Map<String,String> getRegisterProperties() { return this.registerProperties; }
    public void setRegisterProperties(Map<String,String> registerProperties) { this.registerProperties = registerProperties; }

    public String getPhoneCountryCode(){ return this.phoneCountryCode; }
    public void setPhoneCountryCode(String phoneCountryCode){ this.phoneCountryCode = phoneCountryCode; }

    public String getPhoneAreaCode(){ return this.phoneAreaCode; }
    public void setPhoneAreaCode(String phoneAreaCode){ this.phoneAreaCode = phoneAreaCode; }

    public String getPhoneNumber(){ return this.phoneNumber; }
    public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber; }

    public String getPhoneExtension(){ return this.phoneExtension; }
    public void setPhoneExtension(String phoneExtension){ this.phoneExtension = phoneExtension; }

    public String getMobileCountryCode(){ return this.mobileCountryCode; }
    public void setMobileCountryCode(String mobileCountryCode){ this.mobileCountryCode = mobileCountryCode; }

    public String getMobileAreaCode(){ return this.mobileAreaCode; }
    public void setMobileAreaCode(String mobileAreaCode){ this.mobileAreaCode = mobileAreaCode; }

    public String getMobileNumber(){ return this.mobileNumber; }
    public void setMobileNumber(String mobileNumber){ this.mobileNumber = mobileNumber; }

    public Map<String,Map<String, String>> getRegisterAddressDetails() { return this.registerAddressDetails; }
    public void setRegisterAddressDetails( Map<String,Map<String, String>> registerAddressDetails) { this.registerAddressDetails = registerAddressDetails; }

    public Map<String,String> getPartnerDetails() { return this.partnerDetails; }
    public void setPartnerDetails(Map<String,String> partnerDetails) { this.partnerDetails = partnerDetails; }

    public Map<String,String> getOrganisationAddress() { return this.organisationAddress; }
    public void setOrganisationAddress(Map<String,String> organisationAddress) { this.organisationAddress = organisationAddress; }

    public List<Map<String, String>> getGeneralPartnerList() { return this.generalPartnerList; }
    public void setGeneralPartnerList( List<Map<String, String>> generalPartnerList) { this.generalPartnerList = generalPartnerList; }

    public List<Map<String, String>> getLimitedPartnerList() { return this.limitedPartnerList; }
    public void setLimitedPartnerList( List<Map<String, String>> limitedPartnerList) { this.limitedPartnerList = limitedPartnerList; }

    public String getUserUniqueID(){ return this.userUniqueID; }
    public void setUserUniqueID(String userUniqueID){ this.userUniqueID = userUniqueID; }

    public String getInternalUsername(){ return this.internalUsername; }
    public void setInternalUsername(String internalUsername){ this.internalUsername = internalUsername; }

    public String getInternalPassword(){ return this.internalPassword; }
    public void setInternalPassword(String internalPassword){ this.internalPassword = internalPassword; }

    public LocalDate getSysDate(){ return this.sysDate; }
    public void setSysDate(LocalDate sysDate){ this.sysDate = sysDate; }
}
