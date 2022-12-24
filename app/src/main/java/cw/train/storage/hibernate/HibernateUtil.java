package cw.train.storage.hibernate;

import cw.train.passenger.Passenger;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {
  private static final HibernateUtil INSTANCE;
  static {
    INSTANCE = new HibernateUtil();
  }
  @Getter
  private final SessionFactory sessionFactory;
  private HibernateUtil(){
    sessionFactory = new Configuration()
      .addAnnotatedClass(Passenger.class)
      .buildSessionFactory();
  }
  public static HibernateUtil getInstance(){
    return INSTANCE;
  }
  public void close(){
    sessionFactory.close();
  }

  public static void main(String[] args) {
    HibernateUtil util = HibernateUtil.getInstance();
    Session session = util.getSessionFactory().openSession();

//    Passenger passenger = session.get(Passenger.class, 2L);
//    System.out.println("passenger = " + passenger);

    // List all passengers (using HQL query)
//    List<Passenger> passengers = session.createQuery("from Passenger", Passenger.class).list();
//    System.out.println(passengers);

    // Adding new passanger. Use Transaction
//    Transaction transaction = session.beginTransaction();
//      Passenger newPassenger = new Passenger();
//      newPassenger.setName("Henry");
//      newPassenger.setPassport("xs98");
//      session.persist(newPassenger);
//      System.out.println(newPassenger);
//    transaction.commit();

    // Passanger modification. Use Transaction
//    Transaction transaction = session.beginTransaction();
  //    Passenger passenger = session.get(Passenger.class, 2L);
  //    passenger.setName("Petruha");
  //    session.persist(passenger);
//    transaction.commit();
    session.close();
  }
}
