package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		//create a query
		Query theQuery=entityManager.createQuery("from Employee");
		
		//execute query and get result list
		List<Employee> employees=theQuery.getResultList();
		
		//return result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// get employee
		Employee theEmployee=entityManager.find(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		Employee dbEmployee= entityManager.merge(theEmployee);
		theEmployee.setId(dbEmployee.getId());
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Query theQuery=entityManager.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}
	
	
	
	
	
	
}
