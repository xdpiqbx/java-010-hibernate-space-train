package cw.train.ticket;

public interface ITicketDAOService {
  long create(Ticket ticket);
  long getTicketCountToPlanet(Planet planet);
}
