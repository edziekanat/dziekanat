package pl.edu.agh.edeansoffice.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.Students;

@Transactional
@Repository
public interface StudentsDao extends CrudRepository<Students, Integer> {
	
	public List<Students> findAll();
	
	public Students findById(Integer id);
	
	public Students findByStudentsBookNumber(String studentsBookNumber);
	
	public Students findByPassword(String password);
}
