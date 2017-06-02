package pl.edu.agh.edeansoffice.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.edeansoffice.models.Students;
import pl.edu.agh.edeansoffice.persistance.StudentsDao;

@Controller
public class StudentsController {
	
	@Autowired
	private StudentsDao studentsDao;
	
	@RequestMapping(value="/addStudent")
	public String addStudent(Model model, HttpSession session) {
		
		if (session.getAttribute("userLogin") == null) {
    		return "redirect:/login";
		} else {
			return "addStudent";
		}
	}
	
	@RequestMapping(value="/createStudent", method=RequestMethod.POST)
	public String createStudent(@RequestParam(value="name") String name, @RequestParam(value="surname") String surname,
			@RequestParam(value="city") String city, @RequestParam(value="phone") String phone, @RequestParam(value="fieldOfStudy") 
			String fieldOfStudy, @RequestParam(value="yearOfStudy") int yearOfStudy, @RequestParam(value="currentSemester") int currentSemester,
			Model model, HttpSession session) {
		
		if (session.getAttribute("userLogin") == null) 
    		return "redirect:/login";
		
		Students students = new Students(name, surname, city, phone, fieldOfStudy, yearOfStudy, currentSemester);
		
		studentsDao.save(students);
		
		model.addAttribute("message", "Student został dodany");
		
		return "addStudent";
		
	}
	
	@RequestMapping(value="/studentsList")
	public String displayStudentsList(Model model) {
		
		//pobiera dane z bazy danych
		List<Students> students = studentsDao.findAll();
		
		//umiesza pobrane dane do szablonu
		model.addAttribute("students", students);
				
		return "studentsList";
	}
	
	@RequestMapping(value="/removeStudent/{id}", method=RequestMethod.GET)
	public String removeStudent(@PathVariable int id, Model model) {
		
		studentsDao.delete(id);
		
		model.addAttribute("message", "Pracownik został usunięty");
    	
    	return "redirect:/studentsList";
		
	}

}
