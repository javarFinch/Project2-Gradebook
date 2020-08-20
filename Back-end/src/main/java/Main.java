import com.ex.Dao.Dao;
import com.ex.Dao.DaoImpl;
import com.ex.Models.UsersEntity;

public class Main {

    public static void main(String args[])
    {
        Dao dao = new DaoImpl();
        UsersEntity user = (dao.logIn(3, "password"));
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getPassword());
        System.out.println(user.getType());

        //System.out.println(dao.getClassForStudent());
        //System.out.println(dao.getAssignmentsForStudentPerClass());
    }
}



