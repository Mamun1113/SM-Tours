package com.example.smtours;

public class User {
    private String userfullname;
    private String userphone;
    private String useremail;
    private String usergender;
    private String userdateofbirth;
    private String usernewpassword;
    private String parentkey;

    public User(String userfullname, String userphone, String useremail, String usergender, String userdateofbirth, String usernewpassword, String parentkey) {
        this.userfullname = userfullname;
        this.userphone = userphone;
        this.useremail = useremail;
        this.usergender = usergender;
        this.userdateofbirth = userdateofbirth;
        this.usernewpassword = usernewpassword;
        this.parentkey = parentkey;
    }

    public String getParentkey() {
        return parentkey;
    }

    public void setParentkey(String parentkey) {
        this.parentkey = parentkey;
    }

    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsergender() {
        return usergender;
    }

    public void setUsergender(String usergender) {
        this.usergender = usergender;
    }

    public String getUserdateofbirth() {
        return userdateofbirth;
    }

    public void setUserdateofbirth(String userdateofbirth) {
        this.userdateofbirth = userdateofbirth;
    }

    public String getUsernewpassword() {
        return usernewpassword;
    }

    public void setUsernewpassword(String usernewpassword) {
        this.usernewpassword = usernewpassword;
    }
}
