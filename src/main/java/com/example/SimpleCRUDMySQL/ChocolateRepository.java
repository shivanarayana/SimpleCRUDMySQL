package com.example.SimpleCRUDMySQL;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChocolateRepository extends JpaRepository<ChocolateDAO, String> {

}
