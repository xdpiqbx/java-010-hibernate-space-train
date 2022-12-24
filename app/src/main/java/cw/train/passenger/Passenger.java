package cw.train.passenger;

import jakarta.persistence.*;
import lombok.Data;
@Table(name = "passenger") // table name in database
@Entity
@Data
public class Passenger {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This value is auto generated
    @Id // Primary key
    private long id;
    @Column
    private String passport;
    @Column
    private String name;
}
