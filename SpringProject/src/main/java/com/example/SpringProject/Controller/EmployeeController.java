package com.example.SpringProject.Controller;

import com.example.SpringProject.DTO.EmployeeDTO;
import com.example.SpringProject.DTO.EmployeeDeleteDTO;
import com.example.SpringProject.DTO.EmployeeSaveDTO;
import com.example.SpringProject.DTO.EmployeeUpdateDTO;
import com.example.SpringProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
   private EmployeeService employeeService;
    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeSaveDTO employeeSaveDTO)
    {
        String id = employeeService.addEmployee(employeeSaveDTO);
        return id;
    }

    @GetMapping(path = "getAllEmployee")
    public List<EmployeeDTO> getAllEmployee()
    {
        List<EmployeeDTO> getAllEmployees=employeeService.getAllEmployee();
        return getAllEmployees;
    }

    @PutMapping(path = "update")
    public String updateEmployee(@RequestBody EmployeeUpdateDTO employeeupdateDTO)
    {
        String id = employeeService.updateEmployee(employeeupdateDTO);
        return id;

    }


@DeleteMapping(path = "/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id")Integer id)
    {
       boolean deleteEmployee=employeeService.deleteEmployee(id);
       return "deleted";

    }



}
