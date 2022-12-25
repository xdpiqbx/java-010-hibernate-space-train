package cw.train.storage.hibernate;

import cw.train.passenger.Passenger;
import cw.train.storage.DatabaseInitService;
import cw.train.tests.Person;
import cw.train.ticket.Ticket;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
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
      .addAnnotatedClass(Ticket.class)
      .addAnnotatedClass(Person.class)
      .buildSessionFactory();
  }
  public static HibernateUtil getInstance(){
    return INSTANCE;
  }
  public void close(){
    sessionFactory.close();
  }

  public static void main(String[] args) {
    DatabaseInitService.initDb();
    HibernateUtil util = HibernateUtil.getInstance();
    Session session = util.getSessionFactory().openSession();

//    Passenger passenger = session.get(Passenger.class, 2L);
//    System.out.println("passenger = " + passenger);

    // List all passengers (using HQL query)
//    List<Passenger> passengers = session.createQuery("FROM Passenger", Passenger.class).list();
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

    // List all tickets (using HQL query)
//    List<Ticket> tickets = session.createQuery("FROM Ticket", Ticket.class).list();
//    System.out.println(tickets);

    // List all persons (using HQL query)
    List<Person> persons = session.createQuery("FROM Person", Person.class).list();
    System.out.println("persons" + persons);

//    Transaction transaction = session.beginTransaction();
//    Person p = new Person();
//    p.setAdressList(Arrays.asList("address_001", "address_002", "address_003"));
//    session.persist(p);
//    transaction.commit();
    session.close();
  }
}
