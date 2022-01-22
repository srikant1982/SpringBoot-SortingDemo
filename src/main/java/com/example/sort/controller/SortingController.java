package com.example.sort.controller;

import com.example.sort.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SortingController {

    @GetMapping("/test")
    public void test(@RequestBody HashMap<String,Object> map){
        List<Map<String,String>> json = (List<Map<String,String>>) map.get("data");
        System.out.println(map);
        System.out.println(json);

        int i=0;
        for(var item: json){
            i++;
            System.out.println(item.get("test" + i));
        }
    }

    @GetMapping("/")
    public String index(){
        return "Sorting API";
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> SortEmployees(@RequestBody List<Employee> employees){

        Comparator<Employee> comparator = new Comparator<>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getAge() > o2.getAge())
                    return 1;
                else
                    return -1;
            }
        };

        Collections.sort(employees,comparator);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
