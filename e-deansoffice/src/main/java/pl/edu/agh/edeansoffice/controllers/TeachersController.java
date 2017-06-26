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

import pl.edu.agh.edeansoffice.models.Teachers;
import pl.edu.agh.edeansoffice.persistance.TeachersDao;

@Controller
public class TeachersController {
	
	@Autowired
	private TeachersDao teachersDao;
	
	@RequestMapping(value="/addTeacher")
	public String addTeacher(Model model, HttpSession session) {
		
		if(session.getAttribute("userLogin") == null) {
			return "redirect:/login";
		} else {
			return "addTeacher";
		}
	}
	
	@RequestMapping(value="/createTeacher", method=RequestMethod.POST)
	public String createTeacher(@RequestParam(value="name") String name, @RequestParam(value="surname") String surname, @RequestParam(value="city") String city,
			@RequestParam(value="phone") String phone, Model model, HttpSession session) {
		
		if (session.getAttribute("userLogin") == null) 
    		return "redirect:/login";
		
		Teachers teachers = new Teachers(name, surname, city, phone);
		
		teachersDao.save(teachers);
		
		model.addAttribute("message", "Nauczyciel został dodany");
		
		return "addTeacher";
	}
	
	@RequestMapping(value="/teachersList")
	public String displayTeachersList(Model model) {
		
		//pobiera dane z bazy danych
		List<Teachers> teachers = teachersDao.findAll();
		
		//umiesza pobrane dane do szablonu
		model.addAttribute("teachers", teachers);
				
		return "teachersList";
	}
	
	@RequestMapping(value="/removeTeacher/{id}", method=RequestMethod.GET)
	public String removeTeacher(@PathVariable int id, Model model) {
		
		teachersDao.delete(id);
		
		model.addAttribute("message", "Pracownik został usunięty");
    	
    	return "redirect:/teachersList";
		
	}

}
