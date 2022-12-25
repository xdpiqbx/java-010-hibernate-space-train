package cw.train.cli;

import cw.train.ticket.ITicketDAOService;
import cw.train.ticket.Planet;
import cw.train.ticket.TicketDAOServiceHibernate;
import cw.train.ticket.TicketDAOServiceSQL;

import java.sql.SQLException;

public class PlanetStatsState extends CliState{
    public PlanetStatsState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        System.out.print("Enter TO planet: ");
        Planet planet = new PlanetChooser(fsm.getScanner()).ask();
        // TicketDAOServiceSQL ticketDAOService = new TicketDAOServiceSQL(fsm.getConnectionProvider().createConnection());
        ITicketDAOService ticketDAOService = new TicketDAOServiceHibernate();
        long ticketCount = ticketDAOService.getTicketCountToPlanet(planet);
        System.out.println( planet + " found. Ticket count: " + ticketCount );
        fsm.setState(new IdleState(fsm));
    }
}
