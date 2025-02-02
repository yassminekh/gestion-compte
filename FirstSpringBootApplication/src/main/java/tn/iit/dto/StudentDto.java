package tn.iit.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class StudentDto {
	@Include // id est le seul chap à inclure dans equals et hashCode
	private Integer id;// PK
	private String name;
	private char gender;

}