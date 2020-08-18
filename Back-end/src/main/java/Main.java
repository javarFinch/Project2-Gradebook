import com.ex.Services.SessionFactoryHelper;
import org.hibernate.*;

import java.util.List;

public class Main {

    public static void main(String args[])
    {
        SessionFactoryHelper s = new SessionFactoryHelper();
        Session session = s.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx=session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select first_name from admin where id = 1");
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
    }
}



