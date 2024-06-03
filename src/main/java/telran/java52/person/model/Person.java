package telran.java52.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
//@Entity(name = "Citizen")//for jpa сущность
//@Table(name = "person") //привязываемся к
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
	private static final long serialVersionUID = 2881753026638817581L;
	@Id
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Setter
	@Embedded
	Address address;
}
