package telran.java52.person.service;

import java.time.LocalDate;

import telran.java52.dto.AddressDto;
import telran.java52.dto.CityPopulationDto;
import telran.java52.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);
	 
	PersonDto findPersonById(Integer id);
	
	Iterable<PersonDto> findByCity(String city);
	
	Iterable<PersonDto> findByAges(LocalDate minAge, LocalDate maxAge);
	
	PersonDto updateName(Integer id, String name);
	
	Iterable<PersonDto> findByName(String name);
	
	Iterable<CityPopulationDto> getCityPopulation(String city);
	
	PersonDto updateAddress(Integer id, AddressDto addressDto);
	
	PersonDto deletePerson(Integer id);
	
	
	
	
}
