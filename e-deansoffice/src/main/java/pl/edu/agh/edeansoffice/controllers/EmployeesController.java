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

import pl.edu.agh.edeansoffice.models.Employees;
import pl.edu.agh.edeansoffice.models.dao.EmployeesDao;

@Controller
public class EmployeesController {
	
	@Autowired
	private EmployeesDao employeesDao;
	
	@RequestMapping(value="/addEmployee")
	public String addEmployee(Model model, HttpSession session) {
	
		if (session.getAttribute("userLogin") == null) {
    		return "redirect:/login";
		} else {
			return "addEmployee";
		}
	}
	
	@RequestMapping(value="/createEmployee", method=RequestMethod.POST)
	public String createEmployee(@RequestParam(value="name") String name, @RequestParam(value="surname") String surname, 
			@RequestParam(value="city") String city,
			@RequestParam(value="phone") String phone, Model model, HttpSession session) {
		
		if (session.getAttribute("userLogin") == null) 
    		return "redirect:/login";
    		
    	Employees employees = new Employees(name, surname, city, phone);
    	
    	employeesDao.save(employees);
    	
    	model.addAttribute("message", "Pracownik został dodany");
    	
    	return "addEmployee";
	}
	
	@RequestMapping(value="/employeesList")
	public String displayEmployeesList(Model model) {
		
		//pobiera dane z bazy danych
		List<Employees> employees = employeesDao.findAll();
		
		//umiesza pobrane dane do szablonu
		model.addAttribute("employees", employees);
				
		return "employeesList";
	}
	
	//proba usuwania wpisu
	@RequestMapping(value="/removeEmployee/{id}", method=RequestMethod.GET)
	public String removeEmployee(@PathVariable int id, Model model) {
		
		employeesDao.delete(id);
		
		model.addAttribute("message", "Pracownik został usunięty");
    	
    	return "redirect:/employeesList";
		
	}
}
