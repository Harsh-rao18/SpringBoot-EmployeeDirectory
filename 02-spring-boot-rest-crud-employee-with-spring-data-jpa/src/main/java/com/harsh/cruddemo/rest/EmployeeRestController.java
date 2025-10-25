package com.harsh.cruddemo.rest;

import com.harsh.cruddemo.entity.Employee;
import com.harsh.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // expose /employees and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        // also just in case if they pass an id in JSON .... set id to 0
        // this is to force a save of new item .... instead of update
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);
        if (employee == null){
            throw  new RuntimeException("Employee not found");
        }
        employeeService.deleteById(id);

        return "Delete Employee id-" + id;
    }
}
