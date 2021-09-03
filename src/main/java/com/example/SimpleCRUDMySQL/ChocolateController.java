package com.example.SimpleCRUDMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ChocolateController {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    ChocolateService chocolateService;

    @PostMapping(path = "/add")
    public void createChocolate(ChocolateDAO chocolateDAO){
        if (chocolateRepository.findById(chocolateDAO.getName()).isEmpty()) {
            chocolateService.addChocolateDB(chocolateDAO);
        }
    }

    @GetMapping(path = "/get/{name}")
    public Optional<ChocolateDAO> getChocolate(@PathVariable String name) {
        return chocolateService.getChocolateDB(name);
    }

    @PutMapping(path = "/put/{name}")
    public ChocolateDAO putChocolate(@PathVariable String name, @RequestBody ChocolateDAO chocolateDAO){
        ChocolateDAO chocolateDAO2 = chocolateRepository.findById(name).orElse(null);
        if(chocolateDAO2!=null) {
            chocolateDAO2 =  chocolateService.changedChocolateDB(name, chocolateDAO);
        }
        return chocolateDAO2;
    }

    @DeleteMapping(path = "/delete/{name}")
    public void deleteChocolate(@PathVariable String name) {
        ChocolateDAO chocolateDAO = chocolateRepository.findById(name).orElse(null);
        if (chocolateDAO!=null) {
            chocolateService.deleteChocolateDB(chocolateDAO);
        }
    }
}
