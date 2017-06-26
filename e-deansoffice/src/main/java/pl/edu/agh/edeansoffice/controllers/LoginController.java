package pl.edu.agh.edeansoffice.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.edeansoffice.models.Employee;
import pl.edu.agh.edeansoffice.models.Student;
import pl.edu.agh.edeansoffice.models.Teacher;
import pl.edu.agh.edeansoffice.persistance.EmployeeDao;
import pl.edu.agh.edeansoffice.persistance.StudentDao;
import pl.edu.agh.edeansoffice.persistance.TeacherDao;

@Controller
public class LoginController {
	
	@Autowired
	private StudentDao studentsDao;
	@Autowired
	private EmployeeDao employeesDao;
	@Autowired
	private TeacherDao teachersDao;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String displayWelcomePage() {
		return "login";
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String doLoginForUserPage(@RequestParam(value="uid") String login,
			@RequestParam(value="upassword") String pass, Model model, HttpSession session) {
		
		boolean logged = true;
		
		//superuser
		if(login.equals("superuser") && pass.equals("letmein")) {
			session.setAttribute("userLogin", "admin " + login);
        	return "redirect:/welcome";
        	
		} else {
			logged = false;
		}
		//student
        if(logged == false) {
        	
        	List<Student> students = studentsDao.findAll();
        	
        	for (Student student : students) {
				if(student.getStudentsBookNumber().equals(login) && student.getPassword().equals(pass)) {
					session.setAttribute("userLogin", "student " + student.getName() + " " + student.getSurname());
					return "redirect:/student";
				} else {
					logged = false;
				}
			}        	
		}
		//pani grazynka z dziekanatu
        if(logged == false) {
        	
        	List<Employee> employees = employeesDao.findAll();
        	
        	for (Employee employee : employees) {
				if(employee.getName().equals(login) && employee.getPassword().equals(pass)) {
					session.setAttribute("userLogin", "pracownik " + employee.getName() + " " + employee.getSurname());
					return "redirect:/employee";
				} else {
					logged = false;
				}
			}        	
		} 
        
      //nauczyciel
        if(logged == false) {
        	
        	List<Teacher> teachers = teachersDao.findAll();
        	
        	for (Teacher teacher : teachers) {
				if(teacher.getName().equals(login) && teacher.getPassword().equals(pass)) {
					session.setAttribute("userLogin", "nauczyciel " + teacher.getName() + " " + teacher.getSurname());
					return "redirect:/teacher";
				} else {
					logged = false;
				}
			}        	
		} 
        
		return "redirect:/credentials";
	}
	
	@RequestMapping(value="/welcome")
	public String displaySuperUserPage(Model model, HttpSession session) {
		
		if(session.getAttribute("userLogin")==null) {
			return "redirect:/login";
		} else {
			return "superuserpage";
		}
	}
	
	@RequestMapping(value="/student")
	public String displayStudentPage(Model model, HttpSession session) {
		
		if(session.getAttribute("userLogin")==null) {
			return "redirect:/login";
		} else {
			return "studentPage";
		}
	}
	
	@RequestMapping(value="/employee")
	public String displayEmployeePage(Model model, HttpSession session) {
		
		if(session.getAttribute("userLogin")==null) {
			return "redirect:/login";
		} else {
			return "employeePage";
		}
	}
	
	@RequestMapping(value="/teacher")
	public String displayTeacherPage(Model model, HttpSession session) {
		
		if(session.getAttribute("userLogin")==null) {
			return "redirect:/login";
		} else {
			return "teacherPage";
		}
	}
	
	@RequestMapping(value="/logout")
	public String doLogout(Model model, HttpSession session) {
		
		model.addAttribute("message", "Wylogowano!");
		session.removeAttribute("userLogin");
		
		return "login";
	}
	
	@RequestMapping(value="/credentials")
	public String badCredentials(Model model, HttpSession session) {
		
		model.addAttribute("message", "Nieprawidłowy identyfikator lub hasło");
		
		return "login";
	}

}
