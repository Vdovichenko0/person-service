package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
  //построй запрос чтобы вернулся курсор Stream
//  @Query(value = "", nativeQuery = true)
//  @Query("select p from Person p where p.name=?1")
  Stream<Person> findByNameIgnoreCase(String name); // <- JPQL, лучше использовать JPQL - обьектно ориентированный язык sql запросов

//  @Query("select p from Person p where p.address.city=:cityName")
  Stream<Person> findByAddressCityIgnoreCase(@Param("cityName") String city);

  Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
//указывать полный путь
//@Query("select new telran.java52.person.dto.CityPopulationDto(p.address.city, count(p)) from Citizen p group by p.address.city order by count(p) desc")
  @Query("select new telran.java52.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCitiesPopulation();
  
}