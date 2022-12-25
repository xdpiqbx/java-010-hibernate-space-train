package cw.train.passenger;

import java.sql.SQLException;

public interface IPassengerDAOService {
  void create(Passenger passenger) throws SQLException;
  Passenger getByPassport(String passport);
}
