package telran.java52.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity //for jpa
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
