package cw.train.cli;

import cw.train.passenger.Passenger;
import cw.train.passenger.PassengerDAOService;
import cw.train.ticket.Planet;
import cw.train.ticket.Ticket;
import cw.train.ticket.TicketDAOService;

import java.sql.SQLException;

public class AddTicketState extends CliState{
    public AddTicketState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        try {
            PassengerDAOService passengerDAOService = new PassengerDAOService(fsm.getConnectionProvider().createConnection());
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

            TicketDAOService ticketDAOService = new TicketDAOService(fsm.getConnectionProvider().createConnection());
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
