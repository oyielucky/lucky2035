package com.crm.service;
import com.crm.exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService
{




    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    //Constructor based dependency injection
    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper)
    {
        this.employeeRepository=employeeRepository;
        this.modelMapper= modelMapper;
    }

    public EmployeeDto addemployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp =  employeeRepository.save(employee);

        EmployeeDto employeeDto = mapToDto(emp);
        employeeDto.setDate(new Date());
        return employeeDto;
    }

    public void deleteEmployee(Long id)
    {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Long id , EmployeeDto dto)
    {
        Optional<Employee> opEmp = employeeRepository.findById(id);
        // If the employee is found
        Employee employee = opEmp.get();  // Access the employee object
        employee.setName(dto.getName());
        employee.setEmailId(dto.getEmailId());
        employee.setMobile(dto.getMobile());

        employeeRepository.save(employee);
    }

    /*public List<Employee> getEmployee()
    {
        return employeeRepository.findAll();
    } */

    public List<EmployeeDto> getEmployee(int page , int pageSize,String sortBy)
    {
       Pageable pageable =  PageRequest.of(page,  pageSize , Sort.by(sortBy) );
        Page<Employee> all = employeeRepository.findAll(pageable);
        List<Employee>employees = all.getContent();
        List<EmployeeDto> dto =  employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    EmployeeDto mapToDto(Employee employee)
    {
      EmployeeDto dto = modelMapper.map(employee , EmployeeDto.class);
        /* EmployeeDto dto = new EmployeeDto();

        dto.setName(employee.getName());
        dto.setEmailId(employee.getEmailId());
        dto.setId(employee.getId());
        dto.setMobile(employee.getMobile());*/


        return dto;
    }

    Employee mapToEntity(EmployeeDto dto)
    {
        Employee emp = modelMapper.map(dto , Employee.class);
     /*  Employee emp = new Employee();
         emp.setId(dto.getId());
         emp.setName(dto.getName());
        emp.setEmailId(dto.getEmailId());
        emp.setMobile(dto.getMobile());*/
         return emp;

    }

    public EmployeeDto getEmployeeById(long empId)
    {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                // Supplier concept of functional interface
                ()-> new ResourceNotFound("Record not found with id" + empId)
        );

        EmployeeDto dto = mapToDto(employee);
        return  dto;
       /* Optional<Employee> opEmp = employeeRepository.findById(empId);
        Employee employee = opEmp.get();
         return mapToDto(employee);*/
    }
}
