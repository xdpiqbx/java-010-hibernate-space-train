package cw.train.passenger;

import cw.train.storage.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class PassengerDAOServiceHibernate implements IPassengerDAOService{
  @Override
  public void create(Passenger passenger) throws SQLException {
    Session session = HibernateUtil
      .getInstance()
      .getSessionFactory()
      .openSession();
    Transaction transaction = session.beginTransaction();
    session.persist(passenger);
    transaction.commit();
    session.close();
  }

  @Override
  public Passenger getByPassport(String passport) {
    HibernateUtil util = HibernateUtil.getInstance();
    try(Session session = util.getSessionFactory().openSession()){
      Query<Passenger> query = session.createQuery("FROM Passenger WHERE passport = :passport", Passenger.class);
      query.setParameter("passport", passport);
      return query.stream().findFirst().orElse(null);
//      List<Passenger> list = query.list();
//      if(list.isEmpty()){
//        return null; // throw new IllegalStateException("Application is not in an appropriate state. Passenger list is empty")
//      }
//      return list.get(0);
    }
  }
}
