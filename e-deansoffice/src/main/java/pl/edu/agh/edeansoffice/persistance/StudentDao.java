package pl.edu.agh.edeansoffice.persistance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.Student;

@Transactional
@Repository
public interface StudentDao extends CrudRepository<Student, Integer> {
	
	public List<Student> findAll();
	
	public Student findById(Integer id);
	
	public Student findByStudentsBookNumber(String studentsBookNumber);
	
	public Student findByPassword(String password);
}
