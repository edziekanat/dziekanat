package pl.edu.agh.edeansoffice.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.Teachers;

@Transactional
@Repository
public interface TeachersDao extends CrudRepository<Teachers, Integer> {
	
	public List<Teachers> findAll();
	
	public Teachers findById(Integer id);
}
