package cw.train.passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDAOService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByPassportSt;

    public PassengerDAOService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
        "INSERT INTO passenger (passport, name) VALUES (?, ?)"
        );
        getByPassportSt = connection.prepareStatement(
        "SELECT id, name FROM passenger WHERE passport = ?"
        );
    }

    public void create(Passenger passenger){
        try {
            createSt.setString(1, passenger.getPassport());
            createSt.setString(2, passenger.getName());
            createSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Passenger getByPassport(String passport){
        try {
            getByPassportSt.setString(1, passport);
            try(ResultSet rs = getByPassportSt.executeQuery()){
                if(!rs.next()){
                    return null;
                }
                Passenger passenger = new Passenger();
                passenger.setId(rs.getLong("id"));
                passenger.setName(rs.getString("name"));
                passenger.setPassport(passport);
                return passenger;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
