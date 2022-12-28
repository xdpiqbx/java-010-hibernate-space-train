package cw.train.storage.hibernate;

import cw.train.passenger.Passenger;
import cw.train.storage.DatabaseInitService;
import cw.train.tests.Person;
import cw.train.tests.PersonInfo;
import cw.train.tests.Project;
import cw.train.tests.Workplace;
import cw.train.ticket.Ticket;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

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
      .addAnnotatedClass(PersonInfo.class)
      .addAnnotatedClass(Workplace.class)
      .addAnnotatedClass(Project.class)
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

      // Create 5 Persons
//    Transaction transaction = session.beginTransaction();
//    for (int i = 0; i < 5; i++) {
//      Person p = new Person();
//      p.setAdressList(Arrays.asList("address_A: "+i, "address_B: "+i, "address_C:"+i));
//      session.persist(p);
//    }
//    transaction.commit();

      // Set info (names) for all 5
//    List<String> names = Arrays.asList("John", "Bill", "Anthonie", "Abraham", "Vozniak");
//    Transaction transaction = session.beginTransaction();
//    List<Person> persons = session.createQuery("from Person", Person.class).list();
//    int index = 0;
//    for (Person person : persons) {
//      PersonInfo info = new PersonInfo();
//      info.setPersonId(person.getId());
//      info.setName(names.get(index));
//      person.setPersonInfo(info);
//      index++;
//      session.persist(person);
//    }
//    transaction.commit();

      // Set workplaces for all 5
//    List<String> works = Arrays.asList("Google", "Facebook", "Amazon", "AliExpress", "Apple");
//    Transaction transaction = session.beginTransaction();
//    List<Person> persons = session.createQuery("from Person", Person.class).list();
//    int index = 0;
//    for (Person person : persons) {
//      Workplace workplace = new Workplace();
//      workplace.setPlace(works.get(index));
//      workplace.setPerson(person);
//      index++;
//      session.persist(workplace);
//    }
//    transaction.commit();

    // Set project names for all 5
//    List<String> projectNames = Arrays.asList(
//      "Temperature Logger",
//      "Daily Desktop Background",
//      "Personal Development App",
//      "New Music Suggestion Tool",
//      "Pixel Art Generator");
//    List<Person> persons = session.createQuery("from Person", Person.class).list();
//    Transaction transaction = session.beginTransaction();
//    int index = 0;
//    for (String name : projectNames) {
//      Project project = new Project();
//      project.setName(name);
//      persons.get(index).setProjects(Collections.singleton(project));
//      session.persist(project);
//      session.persist(persons.get(index));
//      index++;
//    }
//    transaction.commit();

    // List all persons (using HQL query)
//    List<Person> all = session.createQuery("FROM Person", Person.class).list();
//    for (Person person : all) {
//      System.out.println(person);
//    }

    Person person = session.get(Person.class, 3L);
    System.out.println("person.getProjects() = " + person.getProjects());

//    Workplace workplace = session.get(Workplace.class, 4);
//    System.out.println("workplace.getPerson() = " + workplace.getPerson());
//    System.out.println(workplace);

    session.close();
  }
}
