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

import pl.edu.agh.edeansoffice.models.FieldsOfStudy;
import pl.edu.agh.edeansoffice.models.dao.FieldsOfStudyDao;


@Controller
public class FieldsOfStudyController {
	
	@Autowired
	private FieldsOfStudyDao fieldsOfStudyDao;
	
	@RequestMapping(value="/addField")
	public String addFieldOfStudy(Model model, HttpSession session) {
		
		if (session.getAttribute("userLogin") == null) {
    		return "redirect:/login";
		} else {
			return "addFieldOfStudy";
		}
		
	}
	
	@RequestMapping(value="/createField", method=RequestMethod.POST)
	public String createFieldOfStudy(@RequestParam(value="name") String name, @RequestParam(value="department") String department,
			@RequestParam(value="level") String level, @RequestParam(value="profil") String profil, @RequestParam(value="formOfStudy")
			String formOfStudy, @RequestParam(value="speciality") String speciality, @RequestParam(value="numberOfSemesters")
			int numberOfSemesters, @RequestParam(value="ects") int ects, @RequestParam(value="totalNumberOfHours") int totalNumberOfHours, 
			Model model, HttpSession session) {
		
		if (session.getAttribute("userLogin") == null) 
    		return "redirect:/login";
		
		FieldsOfStudy fieldsOfStudy = new FieldsOfStudy(name, department, level, profil, formOfStudy, speciality, numberOfSemesters, ects, totalNumberOfHours);
		
		fieldsOfStudyDao.save(fieldsOfStudy);
		
		model.addAttribute("message", "Kierunek został dodany");
		
		return "addFieldOfStudy";
	}
	
	@RequestMapping(value="/fieldsOfStudyList")
	public String displayFieldsOfStudyList(Model model) {
		
		//pobiera dane z bazy danych
		List<FieldsOfStudy> fieldsOfStudy = fieldsOfStudyDao.findAll();
		
		//umiesza pobrane dane do szablonu
		model.addAttribute("fieldsOfStudy", fieldsOfStudy);
				
		return "fieldsOfStudyList";
	}
	
	@RequestMapping(value="/removeField/{id}", method=RequestMethod.GET)
	public String removeField(@PathVariable int id, Model model) {
		
		fieldsOfStudyDao.delete(id);
		
		model.addAttribute("message", "Pracownik został usunięty");
    	
    	return "redirect:/fieldsOfStudyList";
		
	}

}
