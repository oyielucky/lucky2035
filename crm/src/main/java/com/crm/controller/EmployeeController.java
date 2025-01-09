package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController
{
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto , BindingResult result) {
      /*  System.out.println(employee.getName());
        System.out.println(employee.getEmailId());
        System.out.println(employee.getMobile());
       */

       if(result.hasErrors())
       {
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
       EmployeeDto employeeDto =  employeeService.addemployee(dto);
       return new ResponseEntity<>( employeeDto , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id)
    {
      employeeService.deleteEmployee(id);
      return new ResponseEntity<>("deleted" , HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<String>  updateEmployee(@RequestParam Long id , @RequestBody EmployeeDto dto)
    {
        employeeService.updateEmployee(id,dto);
        return new ResponseEntity<>("Updated" , HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity< List<EmployeeDto>> getEmployee (
            @RequestParam(name="pageNo" ,required = false,defaultValue="0") int page ,
            @RequestParam(name="pageData" ,required = false,defaultValue="5") int pageSize ,
            @RequestParam(name="sortBy" ,required = false,defaultValue="5") String sortBy)
    {
         List<EmployeeDto> employeesDto = employeeService.getEmployee(page,pageSize,sortBy);
        return new ResponseEntity<>(employeesDto , HttpStatus.OK);

    }

    @GetMapping("/employeeId/{empId}")

     // http://localhost:8080/api/v1/employee/employeeId/1
    public ResponseEntity< EmployeeDto> getEmployeById (@PathVariable long empId)
    {
       EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto , HttpStatus.OK);

    }

}
