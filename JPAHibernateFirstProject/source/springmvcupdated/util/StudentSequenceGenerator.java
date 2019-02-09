package springmvcupdated.util;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import springmvcupdated.entity.AdminUser;
import springmvcupdated.entity.Student;


import java.io.Serializable;

/**
 * Created by kingshuk on 7/8/17.
 */
public class StudentSequenceGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String returnId = "";

        if (o instanceof Student) {
            Student student = (Student) o;
            returnId = student.getDiscipline() + String.valueOf(student.getBatchYear()) + String.valueOf(student.getRollNo());
        } else if (o instanceof AdminUser) {
            AdminUser adminUser = (AdminUser) o;
            returnId = adminUser.getEmployeeCode()+String.valueOf(adminUser.getEmployeeId());
        }
        return returnId;
    }
}
