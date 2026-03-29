package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;


    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos(){

        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getByIdKangaroo(@PathVariable("id") Integer id){

        if ( id <= 0 ) {
            throw new ZooException("Id must be greater than 0 " + id, HttpStatus.BAD_REQUEST);
        }

        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo createKangaroo(@RequestBody Kangaroo kangaroo){

        if ( kangaroo == null || kangaroo.getName().isBlank()) {
            throw new ZooException("Id must be greater than 0 " + kangaroo, HttpStatus.BAD_REQUEST);
        }

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateByIdKangaroo( @PathVariable Integer id, @RequestBody Kangaroo kangaroo){

        if ( id <= 0 ) {
            throw new ZooException("Id must be greater than 0 " + id, HttpStatus.BAD_REQUEST);
        }


        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteByIdKangaroo( @PathVariable int id){

        if ( id <= 0 ) {
            throw new ZooException("Id must be greater than 0 " + id, HttpStatus.BAD_REQUEST);
        }


        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.remove(id);
    }

}

