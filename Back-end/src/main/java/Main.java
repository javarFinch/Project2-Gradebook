import com.ex.Dao.Dao;
import com.ex.Models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    Dao dao;

    @Autowired
    public Main(Dao dao) {
        this.dao = dao;
    }

    public static void main(String args[])
    {
        AbstractApplicationContext ac = new ClassPathXmlApplicationContext("WEB-INF/application-context.xml");

        Main main = ac.getBean("main", Main.class);

        UsersEntity user = (main.dao.logIn(3, "password"));
        System.out.println("User: " + user);
        System.out.println("UserId: " + user.getId());
        System.out.println("UserFName: " + user.getFirstName());
        System.out.println("UserLName: " + user.getLastName());
        System.out.println("UserPassword" + user.getPassword());
        System.out.println("UserType: " + user.getType());

//        ArrayList<ClazzEntity> nList;
//        nList = dao.getClassForStudent(3);
//        for (int i=0; i<nList.size(); i++) {
//            System.out.println("ClassId : " + nList.get(i).getId());
//            System.out.println("ClassName : " + nList.get(i).getClassName());
//            System.out.println("ClassSubject : " + nList.get(i).getClassSubject());
//            System.out.println("ClassTeacherId : " + nList.get(i).getTeacherId());
//        }
//
//        ArrayList<AssignmentEntity> aList = dao.getAssignmentsForStudentPerClass(2,3);
//        for (int i=0; i<aList.size(); i++) {
//            System.out.println("");
//        }

    }
}



