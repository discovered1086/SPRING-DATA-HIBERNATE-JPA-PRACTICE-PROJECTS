package springmvcupdated.entity;


import org.hibernate.annotations.GenericGenerator;
import springmvcupdated.util.DateConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kingshuk on 6/16/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RegistrationInfo {
    @Id
    /*@SequenceGenerator(name = "seqGenerator2", sequenceName = "registration_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator2")*/
    @GenericGenerator(name="seqGenerator7", strategy = "springmvcupdated.util.StudentSequenceGenerator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator7")
    @Column(name="registration_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    //@NotNull(message = "email address can't be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String emailAddress;

    @Column(name="DOB")
    @Convert(converter = DateConverter.class)
    private Date DOB;

    @Column(name = "user_id", unique = true, nullable = false)
    //@NotNull(message = "user Id can't be empty")
    //@UserID(message = "User ID already exists")
    private String userId;

    @Column(name = "password")
    private String password;

    @Transient
    private boolean student;

    /*@Transient
    private String userType;

    @Transient
    private Map<String, String> userTypeMap;

    public RegistrationInfo() {
        userTypeMap=new HashMap<String, String>();
        userTypeMap.put("E","Employee");
        userTypeMap.put("S","Student");
    }*/

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Map<String, String> getUserTypeMap() {
        return userTypeMap;
    }

    public void setUserTypeMap(Map<String, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }*/

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
}
