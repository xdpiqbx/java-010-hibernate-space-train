package cw.train.ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TicketDAOService {

    private final PreparedStatement createSt;
    private final PreparedStatement getMaxIdSt;
    private final PreparedStatement getTicketCountToPlanetSt;
    private ExecutorService insertExecutor;

    public TicketDAOService(Connection connection){
        try {
            insertExecutor = Executors.newSingleThreadScheduledExecutor();
            createSt = connection.prepareStatement(
            "INSERT INTO ticket (passenger_id, from_planet, to_planet) VALUES (?, ?, ?)"
            );
            getMaxIdSt = connection.prepareStatement(
            "SELECT max(id) AS maxId FROM ticket"
            );
            getTicketCountToPlanetSt = connection.prepareStatement(
            "SELECT count(*) AS cnt FROM ticket WHERE to_planet = ?"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // synchronized цілком оправдано для операцій запису
    //  public synchronized long create(Ticket ticket){
    public long create(Ticket ticket){
        Future<Long> ft = insertExecutor.submit(() -> {
            createSt.setLong(1, ticket.getPassengerId());
            createSt.setString(2, ticket.getFrom().name());
            createSt.setString(3, ticket.getTo().name());
            createSt.executeUpdate();
            try(ResultSet rs = getMaxIdSt.executeQuery()){
                rs.next();
                return rs.getLong("maxId");
            }
        });
        try {
            return ft.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public long getTicketCountToPlanet(Planet planet){
        try {
            getTicketCountToPlanetSt.setString(1, planet.name());
            try(ResultSet rs = getTicketCountToPlanetSt.executeQuery()){
                rs.next();
                return rs.getLong("cnt");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
