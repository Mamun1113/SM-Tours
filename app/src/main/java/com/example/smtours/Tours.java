package com.example.smtours;

public class Tours {
    private String useremail;
    private String tourplace;
    private String tourdate;
    private String totalperson;
    private String totalamount;
    private String babyfood;
    private String txnid;
    private String parentkey;
    private String startlocation;
    private String transport;
    private String starttime;

    public Tours(String useremail, String tourplace, String tourdate, String totalperson, String totalamount, String babyfood, String txnid, String parentkey, String startlocation, String transport, String starttime) {
        this.useremail = useremail;
        this.tourplace = tourplace;
        this.tourdate = tourdate;
        this.totalperson = totalperson;
        this.totalamount = totalamount;
        this.babyfood = babyfood;
        this.txnid = txnid;
        this.parentkey = parentkey;
        this.startlocation = startlocation;
        this.transport = transport;
        this.starttime = starttime;
    }

    public String getBabyfood() {
        return babyfood;
    }

    public void setBabyfood(String babyfood) {
        this.babyfood = babyfood;
    }

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

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getParentkey() {
        return parentkey;
    }

    public void setParentkey(String parentkey) {
        this.parentkey = parentkey;
    }

    public String getTourplace() { return tourplace; }

    public void setTourplace(String tourplace) { this.tourplace = tourplace; }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }



    public String getTourdate() {
        return tourdate;
    }

    public void setTourdate(String tourdate) {
        this.tourdate = tourdate;
    }

    public String getTotalperson() {
        return totalperson;
    }

    public void setTotalperson(String totalperson) {
        this.totalperson = totalperson;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
