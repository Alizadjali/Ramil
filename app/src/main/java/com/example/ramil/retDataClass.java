package com.example.ramil;

public class retDataClass {

    private String TripDestination;
    private String TripDate;
    private String childOnTrip;
    private String TripKilometer;
    private String RoadAids;
    private String SickPeople;
    private String PrimaryPhone;
    private String SecondaryPhone;
    private String NumberOfPersons;
    private String FirstAidKit;
    private String DiseaseType;
    private String TripName;


    public String getTripDestination() {
        return TripDestination;
    }

    public String getTripDate() {
        return TripDate;
    }

    public String getChildOnTrip() {return childOnTrip;}

    public String getTripKilometer() {
        return TripKilometer;
    }

    public String getRoadAids() {
        return RoadAids;
    }

    public String getSickPeople() {
        return SickPeople;
    }

    public String getPrimaryPhone() {
        return PrimaryPhone;
    }

    public String getSecondaryPhone() {
        return SecondaryPhone;
    }

    public String getNumberOfPersons() {
        return NumberOfPersons;
    }

    public String getFirstAidKit() {
        return FirstAidKit;
    }

    public String getDiseaseType() {return DiseaseType;}

    public String getTripName() {return TripName;}

    public retDataClass(String tripDestination, String tripDate, String childOnTrip, String tripKilometer, String roadAids, String sickPeople, String primaryPhone, String secondaryPhone, String numberOfPersons, String firstAidKit, String diseaseType, String tripName)
    {
        TripDestination = tripDestination;
        TripDate = tripDate;
        this.childOnTrip = childOnTrip;
        TripKilometer = tripKilometer;
        RoadAids = roadAids;
        SickPeople = sickPeople;
        PrimaryPhone = primaryPhone;
        SecondaryPhone = secondaryPhone;
        NumberOfPersons = numberOfPersons;
        FirstAidKit = firstAidKit;
        DiseaseType = diseaseType;
        TripName = tripName;
    }

    public retDataClass(){


    }

}
