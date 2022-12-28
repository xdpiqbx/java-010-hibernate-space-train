package cw.train.tests;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table(name = "person_info")
@Entity
@Data
public class PersonInfo {
  @Id
  @Column(name = "person_id")
  private long personId;
  @Column(name = "person_name")
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  @ToString.Exclude
  private Person person;
}
