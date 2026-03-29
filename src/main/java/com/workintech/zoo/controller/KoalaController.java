package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAllKoala(){

        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getByIdKoala(@PathVariable Integer id){
        if( id <= 0)
            throw new ZooException("Id argument cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);

        if(!koalas.containsKey(id))
            throw new ZooException("Koala not found with id = " + id, HttpStatus.NOT_FOUND);

        return koalas.get(id);
    }

    @PostMapping
    public Koala createKoala(@RequestBody Koala koala){

        if ( koala == null || koala.getName().isBlank()) {
            throw new ZooException("koala cannot be empty or blank " + koala, HttpStatus.BAD_REQUEST);
        }


        koalas.put(koala.getId(), koala);
        return koala;
    }


    @PutMapping("/{id}")
    public Koala updateByIdKoala(@PathVariable Integer id ,@RequestBody Koala koala){
        if ( id <= 0 ) {
            throw new ZooException("Id must be greater than 0 " + id, HttpStatus.BAD_REQUEST);
        }


        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        koala.setId(id);
        koalas.put(id, koala);
        return koala;
    }



    @DeleteMapping("/{id}")
    public Koala deleteByIdKoala(@PathVariable Integer id){
        if ( id <= 0 ) {
            throw new ZooException("Id must be greater than 0 " + id, HttpStatus.BAD_REQUEST);
        }


        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        return koalas.remove(id);
    }




}
