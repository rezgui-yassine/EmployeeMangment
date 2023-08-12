package com.example.SpringProject.Service;

import com.example.SpringProject.DTO.EmployeeDTO;
import com.example.SpringProject.DTO.EmployeeSaveDTO;
import com.example.SpringProject.DTO.EmployeeUpdateDTO;
import com.example.SpringProject.Entity.Employee;
import com.example.SpringProject.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public String addEmployee(EmployeeSaveDTO employeeSaveDTO) {
        Employee employee=new Employee(
                employeeSaveDTO.getFirstName(),
                employeeSaveDTO.getLastName(),
                employeeSaveDTO.getAddress(),
                employeeSaveDTO.getMobile()
        );

        employeeRepo.save(employee);
        return employee.getFirstName();



    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee>gettEmployees=employeeRepo.findAll() ;
        List<EmployeeDTO>EmployeeDTOList=new ArrayList<>();

        for(Employee e :gettEmployees)
        {
            EmployeeDTO employeeDTO=new EmployeeDTO(
                    e.getId(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getAddress(),
                    e.getMobile()
            );
            EmployeeDTOList.add(employeeDTO);
        }



        return EmployeeDTOList;
    }

    @Override
    public String updateEmployee(EmployeeUpdateDTO employeeupdateDTO) {

        if(employeeRepo.existsById(employeeupdateDTO.getId()))
        {
            Employee employee = employeeRepo.getById(employeeupdateDTO.getId());
            employee.setFirstName(employeeupdateDTO.getFirstName());
            employee.setLastName(employeeupdateDTO.getLastName());
            employee.setAddress(employeeupdateDTO.getAddress());
            employee.setMobile(employeeupdateDTO.getMobile());

            employeeRepo.save(employee);

        }
        else
        {
            System.out.println("Employee Id Not Found");
        }



        return null;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        if(employeeRepo.existsById(id))
        {
            employeeRepo.deleteById(id);
        }
        else
        {
            System.out.println("Employee Id Not Found");
        }


        return true;
    }


}
