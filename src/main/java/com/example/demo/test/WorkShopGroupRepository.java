package com.example.demo.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WorkShopGroupRepository extends JpaRepository<WorkshopGroup, Integer> {

}
