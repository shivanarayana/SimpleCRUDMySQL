package com.example.SimpleCRUDMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepositoryService;

    public String addChocolateDB(ChocolateDAO chocolateDAO){
        List<ChocolateDAO> choco = chocolateRepositoryService.findAll();

        for (ChocolateDAO choc : choco) {
            if (chocolateDAO.getName().equals(choc.getName())) {
                String s = "chocolate already exists";
                return  s;
            }
        }
        chocolateRepositoryService.save(chocolateDAO);
        return "Chocolate Added";
    }

    public Optional<ChocolateDAO> getChocolateDB(String name){
        Optional<ChocolateDAO> chocolateDAO = chocolateRepositoryService.findById(name);
        if(chocolateDAO !=null) {
            return chocolateDAO;
        }
        return null;
    }

    public String getAllChocolateDB(){
        List<ChocolateDAO> chocolateDAOs = chocolateRepositoryService.findAll();
        if(chocolateDAOs == null) {
            throw new ResourceNotFoundException("Student Not exist with id:" );
        }
        return chocolateDAOs.toString();
    }

    public ChocolateDAO changedChocolateDB(String name, ChocolateDAO chocolateDAO){
        ChocolateDAO chocolateDAO1 = chocolateRepositoryService.findById(name).orElse(null);
        if(chocolateDAO1!=null){
            chocolateDAO1.setPrice(chocolateDAO.getPrice());
            chocolateDAO1.setQuantity(chocolateDAO.getQuantity());
            chocolateDAO1.setWeight(chocolateDAO.getWeight());
            chocolateRepositoryService.save(chocolateDAO1);
            return chocolateDAO1;
        }
        else{
            addChocolateDB(chocolateDAO);
            return chocolateDAO;
        }
    }

    public void deleteChocolateDB(ChocolateDAO chocolateDAO){
        chocolateRepositoryService.delete(chocolateDAO);
    }
}
