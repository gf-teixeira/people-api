package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    //injeção de controle e de dependência
    @Autowired //injeta implementação do tipo repository
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){


        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
        //builder().build() -> evita criar direto no construtor,
        // passa de uma forma mais encapsulada

    }

    public List<PersonDTO> listAll() {
        
	    List<Person> allPeople = personRepository.findAll();
    	return allPeople.stream()
		.map(personMapper::toDTO).collect(Collectors.toList());
    }
    private Person verifyIfExists(Long personId) throws PersonNotFoundException{
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException((personId)));
    }

    public PersonDTO findById(Long personId) throws PersonNotFoundException{
       Person person =  verifyIfExists(personId);
        return personMapper.toDTO(person);
    }

    public void deleteById(Long personId) throws PersonNotFoundException{
        verifyIfExists(personId);
        personRepository.deleteById(personId);

    }
}
