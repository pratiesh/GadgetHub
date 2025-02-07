/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.pojo;

/**
 *
 * @author Hp
 */
public class UserPojo {
    private String username;
    private String useremail ;
    private String mobile ;
    private String address ;
    private String pincode ; // private int pincode 
    private String password ;
    private String type ;

    public UserPojo(String username, String useremail, String mobile, String address, String pincode, String password, String type) {
        this.username = username;
        this.useremail = useremail;
        this.mobile = mobile;
        this.address = address;
        this.pincode = pincode;
        this.password = password;
        this.type = type;
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserPojo() {
    }

    public UserPojo(String username, String useremail, String mobile, String address, String pincode, String password) {
        this.username = username;
        this.useremail = useremail;
        this.mobile = mobile;
        this.address = address;
        this.pincode = pincode;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPojo{" + "username=" + username + ", useremail=" + useremail + ", mobile=" + mobile + ", address=" + address + ", pincode=" + pincode + ", password=" + password + "type="+type+'}';
    }
    
    
    
}
