package telran.java52.person.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.dto.AddressDto;
import telran.java52.dto.CityPopulationDto;
import telran.java52.dto.PersonDto;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.exceptions.PersonNotFoundException;
import telran.java52.person.model.Person;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	// В этот момент, пока проводится операция над сущностью Person, транзакция
	// обеспечивает целостность данных
	// и блокирует изменения других операций, гарантируя согласованность и
	// корректность данных.
	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public Iterable<PersonDto> findByCity(String city) {
		return personRepository.findByAddress_City(city).map(p -> modelMapper.map(p, PersonDto.class)).toList();
	}

	@Override
	@Transactional
	public Iterable<PersonDto> findByAges(LocalDate minAge, LocalDate maxAge) {
		return personRepository.findByBirthDateBetween(minAge, maxAge).map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	@Transactional
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		String nameOriginal = person.getName();
		if (nameOriginal != null) {
			person.setName(name);
		}
		person = personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findByName(String name) {
		return personRepository.findByName(name).map(p -> modelMapper.map(p, PersonDto.class)).toList();
	}

	@Override // dont test
	public Iterable<CityPopulationDto> getCityPopulation(String city) {
		return null;
	}

	@Override
	public PersonDto updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		String city = addressDto.getCity();
		if (city != null) {
			person.getAddress().setCity(city);
		}
		String street = addressDto.getStreet();
		if (street != null) {
			person.getAddress().setStreet(street);
		}
		Integer building = addressDto.getBuilding();
		if (building != null) {
			person.getAddress().setBuilding(building);
		}
		person = personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
