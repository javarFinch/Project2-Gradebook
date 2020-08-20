import com.ex.Dao.Dao;
import com.ex.Dao.DaoImpl;
import com.ex.Models.UsersEntity;

public class Main {

    public static void main(String args[])
    {
<<<<<<< HEAD
        SessionFactoryHelper s = new SessionFactoryHelper();
        Session session = s.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx=session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select first_name from admin where id = 100");
            //Query query = session.createQuery("from AdminEntity");
            System.out.println("we are in the session");
            System.out.println(query.list());
//            Query query = session.createQuery(); //You will get Weayher object
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
=======
        Dao dao = new DaoImpl();
        UsersEntity user = (dao.logIn(3, "password"));
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getPassword());
        System.out.println(user.getType());

        //System.out.println(dao.getClassForStudent());
        //System.out.println(dao.getAssignmentsForStudentPerClass());
>>>>>>> 3a19d85419b45b726af315b5e67d78e4bcba5bdd
    }
}



