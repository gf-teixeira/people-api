package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//parte 8 - final
// *Regras de negócio não ficam no controller!
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }
    @GetMapping("/{personId}")
    public PersonDTO getById(@PathVariable Long personId) throws PersonNotFoundException {
        return personService.findById(personId);
    }

    @PutMapping("/{personId}")
    public MessageResponseDTO updateById(@PathVariable Long personId,@RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(personId, personDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid  PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }
    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long personId) throws PersonNotFoundException {
        personService.deleteById(personId);
    }
}

