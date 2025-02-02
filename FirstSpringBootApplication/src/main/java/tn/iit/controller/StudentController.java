package tn.iit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import tn.iit.dto.StudentDto;
import tn.iit.service.StudentService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentController {
	private final StudentService studentService;

	@GetMapping({ "/", "all" })
	public String index(Model model) {// http://localhost:8080/students/all
		model.addAttribute("students", studentService.findAll());
		return "students";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute StudentDto studentDto) {
		studentService.save(studentDto);
		return "redirect:/students/all";
	}

	@PostMapping("/edit/{id}")
	public String editStudent(@ModelAttribute StudentDto studentDto) {
		StudentDto student= (StudentDto) studentService.findAll();
		studentService.update(studentDto);
		return "redirect:/students/all";
	}

	@PostMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		
		studentService.deleteById(id);
		return "redirect:/students/all"; // Rediriger après la suppression
	}

}
