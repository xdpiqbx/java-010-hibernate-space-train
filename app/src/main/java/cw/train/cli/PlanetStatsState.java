package cw.train.cli;

import cw.train.ticket.Planet;
import cw.train.ticket.TicketDAOService;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class PlanetStatsState extends CliState{
    public PlanetStatsState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        System.out.print("Enter TO planet: ");
        Planet planet = new PlanetChooser(fsm.getScanner()).ask();
        try {
            TicketDAOService ticketDAOService = new TicketDAOService(fsm.getConnectionProvider().createConnection());
            long ticketCount = ticketDAOService.getTicketCountToPlanet(planet);
            System.out.println( planet + " found. Ticket count: " + ticketCount );
            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
