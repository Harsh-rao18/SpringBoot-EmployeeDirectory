package com.harsh.cruddemo.dao;

import com.harsh.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
        // execute query and get result
        List<Employee> employees =  theQuery.getResultList();
        // return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee =  entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        // save employee
        Employee dbEmployee = entityManager.merge(employee);
        // return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        Employee employee =  entityManager.find(Employee.class,id);
        entityManager.remove(employee);
    }


}
