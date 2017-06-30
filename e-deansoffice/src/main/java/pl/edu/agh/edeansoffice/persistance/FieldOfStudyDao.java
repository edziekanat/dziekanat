package pl.edu.agh.edeansoffice.persistance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.FieldOfStudy;

@Transactional
@Repository
public interface FieldOfStudyDao extends CrudRepository<FieldOfStudy, Integer> {
	
	public List<FieldOfStudy> findAll();
	
	public FieldOfStudy findById(Integer id);

}
