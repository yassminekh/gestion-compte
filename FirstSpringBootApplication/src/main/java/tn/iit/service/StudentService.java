package tn.iit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tn.iit.dto.StudentDto;

@Service
public class StudentService {
	private List<StudentDto> students = new ArrayList<>();

	public StudentService() {
		students.add(new StudentDto(1, "HBO", 'F'));
		students.add(new StudentDto(2, "HB", 'M'));
	}

	public void save(StudentDto studentDto) {
		students.add(studentDto);
	}

	public List<StudentDto> findAll() {
		return students;
	}

	public void update(StudentDto studentDto) {
		// Chercher l'étudiant avec l'ID correspondant dans la liste
		for (StudentDto student : students) {
			if (student.getId() == studentDto.getId()) {
				// Si l'étudiant est trouvé, on met à jour ses informations
				student.setName(studentDto.getName());
				student.setGender(studentDto.getGender());

				return; // Sortir de la méthode une fois la mise à jour effectuée
			}
		}

		// Si l'étudiant n'est pas trouvé
		throw new EntityNotFoundException("Student not found with ID: " + studentDto.getId());
	}

	public void deleteById(int id) {
		students.remove(id); // Supprime l'étudiant par ID
	}

}
