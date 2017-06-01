package pl.edu.agh.edeansoffice.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.FieldsOfStudy;

@Transactional
@Repository
public interface FieldsOfStudyDao extends CrudRepository<FieldsOfStudy, Integer> {
	
	public List<FieldsOfStudy> findAll();
	
	public FieldsOfStudy findById(Integer id);

}
