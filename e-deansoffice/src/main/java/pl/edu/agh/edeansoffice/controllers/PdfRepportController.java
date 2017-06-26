package pl.edu.agh.edeansoffice.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.edeansoffice.models.Employee;
import pl.edu.agh.edeansoffice.models.GeneratePdfReport;
import pl.edu.agh.edeansoffice.persistance.EmployeeDao;

@Controller
public class PdfRepportController {
	
	@Autowired
	private EmployeeDao employeesDao;
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeesReport() throws IOException{
		List<Employee> employees = (List<Employee>) employeesDao.findAll();
		
		ByteArrayInputStream bis = GeneratePdfReport.employeesReport(employees);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=employeesreport.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
				
	}

}
