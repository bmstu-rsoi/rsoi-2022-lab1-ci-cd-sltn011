package ru.RSOI.Lab1CICD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.RSOI.Lab1CICD.Error.EBadRequestError;
import ru.RSOI.Lab1CICD.Error.ENotFoundError;
import ru.RSOI.Lab1CICD.Model.MPerson;
import ru.RSOI.Lab1CICD.Repos.RPerson;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/persons")
public class CPerson {

    @Autowired
    private RPerson personRepo;

    @GetMapping("")
    public Iterable<MPerson> getAllPersons() {
        return personRepo.findAll();
    }

    @GetMapping("/{id}")
    public MPerson getOnePerson(@PathVariable int id)
    {
        return findPerson(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> addPerson(@RequestBody Map<String, String> values)
    {
        MPerson person = new MPerson();
        fillValues(person, values);
        int newID = personRepo.save(person).getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newID).toUri();

        return  ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePerson(@PathVariable int id)
    {
        try
        {
            personRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {

        }
    }

    @PatchMapping("/{id}")
    public MPerson patchPerson(@PathVariable int id, @RequestBody Map<String, String> values)
    {
        MPerson person = findPerson(id);
        fillValues(person, values);
        return personRepo.save(person);
    }

    private MPerson findPerson(int id)
    {
        return personRepo.findById(id).orElseThrow(() -> new ENotFoundError("Person not found!"));
    }

    private MPerson fillValues(MPerson person, Map<String, String> values)
    {
        if (values.containsKey("age"))
        {
            try {
                person.age = Integer.parseInt(values.get("age"));
            }
            catch (NumberFormatException e)
            {
                EBadRequestError error = new EBadRequestError(
                        "Bad request!",
                        new ArrayList<String>(){{add("Wrong age passed!");}}
                );
                throw error;
            }
        }
        if (values.containsKey("name"))
        {
            person.name = values.get("name");
        }
        if (values.containsKey("address"))
        {
            person.address = values.get("address");
        }
        if (values.containsKey("work"))
        {
            person.work = values.get("work");
        }

        return person;
    }
}
