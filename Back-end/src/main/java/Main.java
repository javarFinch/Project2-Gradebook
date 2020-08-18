import com.ex.Services.SessionFactoryHelper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {

    public static void main(String args[])
    {
        SessionFactoryHelper s = new SessionFactoryHelper();
        Session session = s.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx=session.beginTransaction();
            System.out.println("we are in the session");
//            Query query = session.createQuery(); //You will get Weayher object
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }}



