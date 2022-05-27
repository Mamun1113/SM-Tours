package com.example.smtours;

public class TourList {
    private String tourplace;
    private String tourdate;
    private String totalperson;
    private String useremail;
    private String parentkey;
    private String startlocation;
    private String transport;
    private String starttime;

    TourList() {

    }

    public TourList(String tourplace, String tourdate, String totalperson, String startlocation, String transport, String starttime) {
        this.tourplace = tourplace;
        this.tourdate = tourdate;
        this.totalperson = totalperson;
        this.startlocation = startlocation;
        this.transport = transport;
        this.starttime = starttime;
    }

    /*    public TourList(String tourplace, String tourdate, String totalperson) {

        if(totalperson.equals("2")) {
            this.tourplace = tourplace;
            this.tourdate = tourdate;
            this.totalperson = totalperson;
        }
    }*/

    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getParentkey() {
        return parentkey;
    }

    public void setParentkey(String parentkey) {
        this.parentkey = parentkey;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setTourplace(String tourplace) {
        this.tourplace = tourplace;
    }

    public void setTourdate(String tourdate) {
        this.tourdate = tourdate;
    }

    public void setTotalperson(String totalperson) {
        this.totalperson = totalperson;
    }

    public String getTourplace() {
        return tourplace;
    }

    public String getTourdate() {
        return tourdate;
    }

    public String getTotalperson() {
        return totalperson;
    }
}
