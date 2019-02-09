package springmvcupdated.entity;





import springmvcupdated.util.DateConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kingshuk on 7/8/17.
 */
@Entity
@Table(name="admin_user")
public class AdminUser extends RegistrationInfo{

    //@NotNull(message = "Employee Code can't be empty")
    //@EmployeeCodeValidation
    @Enumerated(EnumType.STRING)
    @Column(name="employee_code")
    private EmployeeCode employeeCode;

    //@NotNull(message = "Employee Code can't be empty")
    @Column(name="employee_id")
    private Integer employeeId;

    @Column(name="DOH")
    @Convert(converter = DateConverter.class)
    private Date DOH;

    @Override
    public String toString() {
        return "AdminUser{}";
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDOH() {
        return DOH;
    }

    public void setDOH(Date DOH) {
        this.DOH = DOH;
    }

    public EmployeeCode getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(EmployeeCode employeeCode) {
        this.employeeCode = employeeCode;
    }
}
