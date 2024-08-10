package com.empmgt.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empmgt.entity.Employee;
import com.empmgt.exception.ResourceNotFoundException;
import com.empmgt.repository.EmployeeRepository;
import com.empmgt.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
//@Transactional no need to use this annotation becoz spring jpa already provide this annotation
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	 
    //@Autowired @Autowired annotation can be omitted.spring will use the constructor and inject all necessary dependencies.

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		/*Optional<Employee> employee=employeeRepository.findById(id);
		if (employee.isPresent())
		{
			return employee.get();
		}
		else
		{
			throw new ResourceNotFoundException("Employee", "Id", id)
		}*/
		
		//Lambda expression
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	}



	@Override
	public Employee updateEmployee(Employee employee, Long id) {
		//we need to check whether given employee id is exist or not
		
		Employee existEmployee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employeee","Id",id));
		existEmployee.setFirstname(employee.getFirstname());
		existEmployee.setLastname(employee.getLastname());
		existEmployee.setEmailid(employee.getEmailid());
		employeeRepository.save(existEmployee);
		return existEmployee;
		
	}

	@Override
	public void deleteEmployee(Long id) {
		
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id", id));
		employeeRepository.deleteById(id);
	}
	
	// Whenever we have manadatory parameters we will used constructor injection.
	//when optional parameter then use setter injection.
	//Starting with spring 4.3,if a class which is configured as a spring bean has only one constructor
	//@Autowired annotation can be omitted.spring will use the constructor and inject all necessary dependencies.

}
