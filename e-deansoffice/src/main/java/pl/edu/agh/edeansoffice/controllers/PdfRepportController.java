package pl.edu.agh.edeansoffice.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.edeansoffice.models.Employee;
import pl.edu.agh.edeansoffice.models.GeneratePdfReport;
import pl.edu.agh.edeansoffice.models.Student;
import pl.edu.agh.edeansoffice.persistance.EmployeeDao;
import pl.edu.agh.edeansoffice.persistance.StudentDao;

@Controller
public class PdfRepportController {

	@Autowired
	private EmployeeDao employeesDao;
	@Autowired
	private StudentDao studentsDao;

	@RequestMapping(value = "/reports")
	public String displayEmployeesList(Model model, HttpSession session) {

		model.addAttribute("message", "Test");
		
		if (session.getAttribute("userLogin") == null) {
    		return "redirect:/login";
		} else {
			return "reports";
		}
	}

	@RequestMapping(value = "/createReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeesReport(Model model, @RequestParam(value="typeOfReport") String typeOfReport) throws IOException {
		
		ByteArrayInputStream bis = null;
		
		if (typeOfReport.equals("listOfStudents")){
			List<Student> students = (List<Student>) studentsDao.findAll();
			bis = GeneratePdfReport.studentsReport(students);
		} else if(typeOfReport.equals("listOfWorkers")){
			List<Employee> employees = (List<Employee>) employeesDao.findAll();
			bis = GeneratePdfReport.employeesReport(employees);
		}
		
/*		List<Student> students = (List<Student>) studentsDao.findAll();
		
		if (typeOfReport == "listOfStudents"){
			model.addAttribute("message", "Test222");
		}
				
		ByteArrayInputStream bis = GeneratePdfReport.studentsReport(students);*/
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=employeesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
/*	@RequestMapping(value = "/createReport", method = RequestMethod.GET)
	public String displaymessage(Model model, @RequestParam(value="typeOfReport") String typeOfReport) {
		

		if (typeOfReport.equals("listOfStudents")){
			model.addAttribute("message", typeOfReport);
		} else if(typeOfReport.equals("listOfWorkers")){
			model.addAttribute("message", typeOfReport);
		}
		
		return "reports";
	}*/

}
