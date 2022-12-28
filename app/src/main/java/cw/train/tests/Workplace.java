package cw.train.tests;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table(name = "workplace")
@Entity
@Data
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ToString.Exclude
  @ManyToOne
  private Person person;
  @Column(name = "place")
  private String place;
}
