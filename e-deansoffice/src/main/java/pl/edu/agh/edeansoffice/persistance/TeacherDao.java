package pl.edu.agh.edeansoffice.persistance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.Teacher;

@Transactional
@Repository
public interface TeacherDao extends CrudRepository<Teacher, Integer> {
	
	public List<Teacher> findAll();
	
	public Teacher findById(Integer id);
}
