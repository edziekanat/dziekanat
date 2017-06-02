package pl.edu.agh.edeansoffice.persistance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.agh.edeansoffice.models.Employees;

@Transactional
@Repository
public interface EmployeesDao extends CrudRepository<Employees, Integer> {

	//nazwy metod muszą być takie jak poniżej, wtedy wszystko za nas zrobi hibernate np. findByName itd.
	public List<Employees> findAll();
	
	public Employees findById(Integer id);
}
