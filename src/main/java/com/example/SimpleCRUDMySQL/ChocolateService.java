package com.example.SimpleCRUDMySQL;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    public void addChocolateDB(ChocolateDAO chocolateDAO){
        chocolateRepository.save(chocolateDAO);
    }

    public Optional<ChocolateDAO> getChocolateDB(String name){
        Optional<ChocolateDAO> chocolateDAO = chocolateRepository.findById(name);
        if(chocolateDAO !=null) {
            return chocolateDAO;
        }
        return null;
    }

    public ChocolateDAO changedChocolateDB(String name, ChocolateDAO chocolateDAO){
        ChocolateDAO chocolateDAO1 = chocolateRepository.findById(name).orElse(null);
        if(chocolateDAO1!=null){
            chocolateDAO1.setPrice(chocolateDAO.getPrice());
            chocolateDAO1.setQuantity(chocolateDAO.getQuantity());
            chocolateDAO1.setWeight(chocolateDAO.getWeight());
            chocolateRepository.save(chocolateDAO1);
            return chocolateDAO1;
        }
        else{
            addChocolateDB(chocolateDAO);
            return chocolateDAO;
        }
    }

    public void deleteChocolateDB(ChocolateDAO chocolateDAO){
        chocolateRepository.delete(chocolateDAO);
    }
}
