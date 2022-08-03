package com.example.test.controller;

import com.example.test.dao.PersonDao;
import com.example.test.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personDao.getAll());
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Person> getPersonById(@RequestParam int id) {
        return ResponseEntity.ok(personDao.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Person person ) {
        personDao.save(person);
        return ResponseEntity.ok("created");
    }

}
