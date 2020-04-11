package fr.micronaut.presentation.petcore.domains.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "pet")
public class Pet {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "number")
  private Integer number;

  @Column(name = "price")
  private Double price;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn
  private Type type;
}
