package telran.java52.person.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;

@Getter
//настраиваем наследование для jakson 
//чтобы получить тип что мы добавляем и тд,персон или что то другое
//храним имя json в качестве проперти и читаем с запрос по тегу type
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
	//передаем типы
	@Type(name = "child", value = ChildDto.class), //если child то класс ChildDto
	@Type(name = "employee", value = EmployeeDto.class),
	@Type(name = "person", value = PersonDto.class)
})
public class PersonDto {
	Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address; 
}
