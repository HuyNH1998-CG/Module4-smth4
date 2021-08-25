package bigg.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date dob;
    private String address;
    private String phoneNumber;
    private String email;
    private String image;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    public Student() {
    }

    public Student(String name, Date dob, String address, String phoneNumber, String email, String image, Classroom classroom) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
        this.classroom = classroom;
    }

    public Student(String name, Date dob, String address, String phoneNumber, String email, Classroom classroom) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.classroom = classroom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
