package cw.train.tests;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Table(name = "person")
@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // This value is auto generated
  private long id;
  @ElementCollection
  @CollectionTable(
    name = "person_address",
    joinColumns = @JoinColumn(name = "person_id")
  )
  @Column(name="address")
  private List<String> adressList;
}
