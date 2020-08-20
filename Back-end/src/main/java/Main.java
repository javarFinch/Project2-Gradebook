import com.ex.Dao.Dao;
import com.ex.Dao.DaoImpl;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;

import java.util.ArrayList;

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

        DaoImpl d = new DaoImpl();

        ArrayList<ClazzEntity> nList = new ArrayList<>();
        nList = d.getClassForStudent(3);
        System.out.println("in main : " + nList.get(0).getClassName());

        //System.out.println(dao.getClassForStudent());
        //System.out.println(dao.getAssignmentsForStudentPerClass());
    }
}



