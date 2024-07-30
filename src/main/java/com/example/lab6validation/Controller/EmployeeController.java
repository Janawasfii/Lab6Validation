package com.example.lab6validation.Controller;

import com.example.lab6validation.APIResponse.API;
import com.example.lab6validation.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")

public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<Employee>();


@GetMapping("/get")
public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employees);}


    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new API("Successfully added employee"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @Valid @RequestBody Employee employee, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new API("Successfully updated employee"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employees.remove(employee);
                return ResponseEntity.status(200).body(new API("Succefully deleted"));
            }
        }
        return ResponseEntity.status(400).body(new API("Employee id not found"));
    }


    @GetMapping("/search/{position}")
    public ResponseEntity searchEmployee(@PathVariable String position) {
        ArrayList employeess1 = new ArrayList();
        for (Employee employee : employees) {
            if(position.equals("Supervisor" )|| position.equals("Coordinator")) {
            if (employee.getPosition().equals(position)) {
                employeess1.add(employee);
                return ResponseEntity.status(200).body(employees);
            }
        }}
        return ResponseEntity.status(400).body(new API("Employee not found"));
    }

@GetMapping("/find/{min}/{max}")
public ResponseEntity findEmployee(@PathVariable int min, @PathVariable int max) {
        ArrayList employeess2 = new ArrayList();
        for (Employee employee : employees) {
        if (min <= employee.getAge() && employee.getAge() <= max) {
        employeess2.add(employee);
        return ResponseEntity.status(200).body(employees);}}
        return ResponseEntity.status(400).body(employees);}

@PutMapping("/updates/{id}")
public ResponseEntity updateAnnual(@PathVariable String id) {
        for (Employee employee1 : employees) {
        if (employee1.getId().equals(id)) {
        if (employee1.getOnLeave().equals(false)) {
        if (employee1.getAnnualLeave() >= 1) {
        employee1.setOnLeave(true);
        employee1.setAnnualLeave(employee1.getAnnualLeave() - 1);
        return ResponseEntity.status(200).body(new API("Succefully updated"));}
        }
        }}
        return  ResponseEntity.status(400).body(new API("Employee not found"));}

@GetMapping("/gets")
    public ResponseEntity getsEmployeesNoAnnualLeave() {
    ArrayList employeess = new ArrayList();
    for (Employee employee : employees) {
        if (employee.getAnnualLeave() == 0) {
            employeess.add(employee);
            return ResponseEntity.status(200).body(employees);
        }
    }return  ResponseEntity.status(400).body(new API("Employee not found"));
}

@PutMapping("/change/{SuperID}/{CoorID}")
    public ResponseEntity changeEmployee(@PathVariable String SuperID,@PathVariable String CoorID) {
    for (Employee employee : employees) {
        if (employee.getId().equals(SuperID)) {
            if (employee.getPosition().equals("Supervisor")) {
                for (Employee employee1 : employees) {
                    if (employee1.getId().equals(CoorID)) {
                        if (employee1.getPosition().equals("Coordinator")) {

                            if (employee1.getAge() >= 30) {

                                if (employee1.getOnLeave().equals(false)) {
                                    employee1.setPosition("Supervisor");
                                   return ResponseEntity.status(200).body(new API("Match the requirments"));

                                }
                            }
                        }

                    }
                }
            }
        }



    }return ResponseEntity.status(400).body(new API("Doesn't match the requirments"));
}

}














