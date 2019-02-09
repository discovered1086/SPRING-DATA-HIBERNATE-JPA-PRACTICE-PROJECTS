package commonutility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SpringHibernateUtil extends HibernateUtil {


    @Override
    public SessionFactory createSessionFactory() {
        try{
            registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            metaData = new MetadataSources(registry).getMetadataBuilder().build();

            //Another way of doing it. the advantage of this is that every time we add a new class, we don't have to
            //Add it in the hibernate configuration file
            /*return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EngStudent.class)
                    .addAnnotatedClass(EngCourse.class)
                    .addAnnotatedClass(Discipline.class)
                    .addAnnotatedClass(ExamResults.class)
                    .addAnnotatedClass(StudentExamResult.class).buildSessionFactory();*/

        }catch(Exception ex){
            ex.printStackTrace();
            if(registry!=null){
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return metaData != null ? metaData.getSessionFactoryBuilder().build() : null;
    }
}
