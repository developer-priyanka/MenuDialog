package my.assignment.menudialog;


/**
 * Created by root on 7/27/16.
 */

public class Person {
    String name;
    String phone;
    String dob;

    public Person(String name,String phone,String dob){
        this.name=name;
        this.phone=phone;
        this.dob=dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }




}
