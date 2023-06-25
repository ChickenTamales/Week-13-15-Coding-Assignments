package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Employee;

/* Create a new DAO interface named EmployeeDao.  
 * The interface should extend JpaRepository. It is very similar to PetStoreDao 
 * but it refers to an Employee object in the Generic. */

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
