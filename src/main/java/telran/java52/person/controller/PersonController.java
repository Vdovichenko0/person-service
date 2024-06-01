package telran.java52.person.controller;

import java.time.LocalDate;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.dto.AddressDto;
import telran.java52.dto.CityPopulationDto;
import telran.java52.dto.PersonDto;
import telran.java52.person.service.PersonService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

	final PersonService personService;

	@PostMapping // /person
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@GetMapping("/city/{city}")
	@Transactional
	public Iterable<PersonDto> findByCity(@PathVariable String city) {
		return personService.findByCity(city);
	}

	@GetMapping("/ages/{minAge}/{maxAge}")
	@Transactional
	public Iterable<PersonDto> findByAges(@PathVariable int minAge, @PathVariable int maxAge) {
		LocalDate from = LocalDate.now().minusYears(maxAge);
		LocalDate to = LocalDate.now().minusYears(minAge);
		return personService.findByAges(from, to);
	}
	
	@PutMapping("/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}
	
	@GetMapping("/name/{name}")
	@Transactional
	public Iterable<PersonDto> findByName(@PathVariable String name){
		return personService.findByName(name);
	}
	////// dont test 
	public Iterable<CityPopulationDto> getCityPopulation(String city){
		return null;
	}
	
	@PutMapping("{id}/address")
	public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updateAddress(id, addressDto);
	}
	
	@DeleteMapping("{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
}
