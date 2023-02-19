package com.mikro.phone_contact_system.service;

import com.mikro.phone_contact_system.dto.ContactDto;
import com.mikro.phone_contact_system.exception.ElementNotFoundException;
import com.mikro.phone_contact_system.mapper.ContactMapper;
import com.mikro.phone_contact_system.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brume
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService {
    private final ContactMapper mapper;
    private final ContactRepository repository;

    /** method to create a contact
     * @param contactDto
     * @return ContactDto
     */
    public ContactDto createContact(ContactDto contactDto) {
        var contact = mapper.mapDtoToModel(contactDto);
        return mapper.mapModelToDto(repository.save(contact));
    }

    public ContactDto updateContact(Long id, ContactDto contactDto) {
        findById(id);
        return createContact(contactDto);
    }

    /** method to get a contact
     * @param id
     * @return ContactDto
     * @throws ElementNotFoundException
     */
    public ContactDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::mapModelToDto)
                .orElseThrow(() -> new ElementNotFoundException( id));
    }

    /**
     * method to get all created contact
     * @return  List<ContactDto>
     */
    public List<ContactDto> getAllContacts() {
        return mapper.mapModelsToDtos(repository.findAll());
    }

    /**
     * method to delete a contact
     * @param id
     * @return String
     */
    public String delete(Long id) {
        var contact = repository.existsById(id);
        if (contact) {
            repository.deleteById(id);
            return String.format("Contact with id %s hase been deleted", id);
        } else {
            throw new ElementNotFoundException( id);
        }
    }
}
