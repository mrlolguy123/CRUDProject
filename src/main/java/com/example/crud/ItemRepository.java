package com.example.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByOrderByNameAsc();
    List<Item> findAllByOrderByIdAsc();
    List<Item> findAllByNameContains(String name);
    List<Item> findAllByIdEquals(Integer id);

}
