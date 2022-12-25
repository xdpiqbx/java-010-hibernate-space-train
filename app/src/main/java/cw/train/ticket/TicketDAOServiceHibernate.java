package cw.train.ticket;

import cw.train.storage.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class TicketDAOServiceHibernate implements ITicketDAOService{
  @Override
  public long create(Ticket ticket) {
    try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
      Transaction transaction = session.beginTransaction();
      session.persist(ticket);
      transaction.commit();
      return ticket.getId();
    }
  }

  @Override
  public long getTicketCountToPlanet(Planet planet) {
    // "SELECT count(*) AS cnt FROM ticket WHERE to_planet = ?"
    try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
//      Query<Long> query = session
//        .createQuery(
//          "SELECT count(id) FROM Ticket t WHERE t.to = :to",
//          Long.class);
//      query.setParameter("to", planet);
//      return query.getSingleResult();

      NativeQuery<Long> query = session
        .createNativeQuery(
          "SELECT count(*) FROM ticket WHERE to_planet = :to",
          Long.class);
      query.setParameter("to", planet.name());
      System.out.println("query.getResultList() = " + query.getResultList());
      return query.getSingleResult();
    }
  }
}
