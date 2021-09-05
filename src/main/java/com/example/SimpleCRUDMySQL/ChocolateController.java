package com.example.SimpleCRUDMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChocolateController {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    ChocolateService chocolateService;

    @PostMapping(path = "/add")
    public String createChocolate(@RequestBody ChocolateDAO chocolateDAO){
       return chocolateService.addChocolateDB(chocolateDAO);
    }

    @GetMapping(path = "/get/{name}")
    public Optional<ChocolateDAO> getChocolate(@PathVariable String name) {
        return chocolateService.getChocolateDB(name);
    }

    @GetMapping(path = "/getAll")
    public String getAllChocolate() {
        return chocolateService.getAllChocolateDB();
    }

    @PutMapping(path = "/put/{name}")
    public ChocolateDAO putChocolate(@PathVariable String name, @RequestBody ChocolateDAO chocolateDAO){
        ChocolateDAO chocolateDAO2 = chocolateRepository.findById(name).orElseThrow(()-> new ResourceNotFoundException("Student Not exist with id:"));;
        if(chocolateDAO2!=null) {
            chocolateDAO2 =  chocolateService.changedChocolateDB(name, chocolateDAO);
        }
        return chocolateDAO2;
    }

    @DeleteMapping(path = "/delete/{name}")
    public void deleteChocolate(@PathVariable String name) {
        ChocolateDAO chocolateDAO = chocolateRepository.findById(name).orElseThrow(()-> new ResourceNotFoundException("Student Not exist with id:"));
        if (chocolateDAO!=null) {
            chocolateService.deleteChocolateDB(chocolateDAO);
        }
    }
}
