package com.empmgt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empmgt.entity.Employee;
import com.empmgt.exception.ResourceNotFoundException;
import com.empmgt.repository.EmployeeRepository;
import com.empmgt.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//create employee rest api
	//ResoponseEntity is used to generate complete response that is status code,body etc
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	//create get all  employees rest api
	@GetMapping
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployee();
	}
	
	//build getEmployeeById rest api
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
	}
	
	//build updateEmployee rest api
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id ),HttpStatus.OK);
	}
	
	//build deleteEmployee rest api
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletEmployee(@PathVariable("id") long id)
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee Deleted Successfully",HttpStatus.OK);
	}
	
	
	
	
	
	
	/*@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@GetMapping
	public List<Employee> getAllEmployee()
	{
		return employeeRepository.findAll();
	}
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	@GetMapping("{id}")
	
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee is not exist with id"+id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails)
	{
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee is not exists with id"+id));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmailid(employeeDetails.getEmailid());
		Employee updateEmployee=employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id)
	{
		Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with id"+id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response=new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
		
	}*/
	
	
	
	
	

}
