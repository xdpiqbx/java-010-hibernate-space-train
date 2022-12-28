package cw.train.tests;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@Table(name = "person")
@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  // Use separate table
//  @ElementCollection
//  @CollectionTable( name = "person_address", joinColumns = @JoinColumn(name = "person_id") )
//  @Column(name="address")
  // Use the same table
  @Column(name = "addresses")
  @Convert(converter = AddressConverter.class)
  private List<String> adressList;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id", referencedColumnName = "person_id")
  private PersonInfo personInfo;

  @OneToMany(mappedBy = "person")
  private List<Workplace>workplaces;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "person_project",
    joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "project_id")
  )
  private Set<Project> projects;
}