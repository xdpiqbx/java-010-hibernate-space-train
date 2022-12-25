package cw.train.cli;

import cw.train.passenger.IPassengerDAOService;
import cw.train.passenger.Passenger;
import cw.train.passenger.PassengerDAOServiceHibernate;
import cw.train.ticket.*;

import java.sql.SQLException;

public class AddTicketState extends CliState{
    public AddTicketState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
//            IPassengerDAOService passengerDAOService = new PassengerDAOServiceSQL(fsm.getConnectionProvider().createConnection());
            IPassengerDAOService passengerDAOService = new PassengerDAOServiceHibernate();
            System.out.print("Enter passenger passport: ");
            String passport = fsm.getScanner().nextLine();
            Passenger passenger = passengerDAOService.getByPassport(passport);
            if(passenger != null){
                System.out.println("Passenger " + passenger.getName() + " found. Enter FROM planet:");
            }else{
                System.out.println("Enter passenger name: ");
                passenger = new Passenger();
                String passengerName = fsm.getScanner().nextLine();
                passenger.setName(passengerName);
                passenger.setPassport(passport);
                passengerDAOService.create(passenger);

                passenger = passengerDAOService.getByPassport(passport);

                System.out.println("Passenger saved. Enter FROM planet: ");
            }
            Planet from = new PlanetChooser(fsm.getScanner()).ask();
            System.out.println(from + " found. Enter TO planet");
            Planet to = new PlanetChooser(fsm.getScanner()).ask();

//            TicketDAOServiceSQL ticketDAOService = new TicketDAOServiceSQL(fsm.getConnectionProvider().createConnection());
            ITicketDAOService ticketDAOService = new TicketDAOServiceHibernate();
            Ticket ticket = new Ticket();
            ticket.setPassengerId(passenger.getId());
            ticket.setFrom(from);
            ticket.setTo(to);
            long ticketId = ticketDAOService.create(ticket);
            System.out.println(to + " found. Ticket ordered, ID: " + ticketId);
            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
