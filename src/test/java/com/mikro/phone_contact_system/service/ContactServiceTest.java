package com.mikro.phone_contact_system.service;

import com.mikro.phone_contact_system.dto.ContactDto;
import com.mikro.phone_contact_system.mapper.ContactMapper;
import com.mikro.phone_contact_system.model.Contact;
import com.mikro.phone_contact_system.repository.ContactRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Brume
 **/
@ExtendWith(MockitoExtension.class)
class ContactServiceTest {
    Long id = RandomUtils.nextLong();
    String address = RandomStringUtils.randomAlphanumeric(11);
    String phoneNumber = RandomStringUtils.randomNumeric(12);
    String name = RandomStringUtils.randomAlphabetic(13);
    @Mock
    private ContactMapper mapper;
    @Mock
    private ContactRepository repository;

    @InjectMocks
    private ContactService service;

    @Test
    void createContact() {
        var contact = getContact();
        var contactDto = getContactDto();

        when(mapper.mapDtoToModel(contactDto)).thenReturn(contact);
        when(mapper.mapModelToDto(contact)).thenReturn(contactDto);
        when(repository.save(contact)).thenReturn(contact);

        var response = service.createContact(contactDto);
        assertNotNull(response);

        verify(repository, times(1)).save(contact);
    }

    @Test
    void updateContact() {
        var contact = getContact();
        var contactDto = getContactDto();

        contact.setId(1L);
        contactDto.setId(1L);

        when(mapper.mapDtoToModel(contactDto)).thenReturn(contact);
        when(mapper.mapModelToDto(contact)).thenReturn(contactDto);
        when(repository.findById(1L)).thenReturn(Optional.of(contact));
        when(repository.save(contact)).thenReturn(contact);


        var response = service.updateContact(1L, contactDto);
        assertNotNull(response);
        assertEquals(1L, response.getId());

        verify(repository, times(1)).save(contact);
    }

    @Test
    void findById() {
        var contact = getContact();
        var contactDto = getContactDto();
        var id = contactDto.getId();

        when(mapper.mapModelToDto(contact)).thenReturn(contactDto);
        when(repository.findById(id)).thenReturn(Optional.of(contact));

        var response = service.findById(id);
        assertNotNull(response);
        assertEquals(contact.getId(), response.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void getAllContacts() {
        var contacts = List.of(getContact(), getContact());

        var contactDtos = List.of(getContactDto(), getContactDto());

        when(mapper.mapModelsToDtos(contacts)).thenReturn(contactDtos);
        when(repository.findAll()).thenReturn(contacts);

        var response = service.getAllContacts();

        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void delete() {
        when(repository.existsById(1L)).thenReturn(true);

        var response = service.delete(1L);
        assertNotNull(response);

        verify(repository, times(1)).deleteById(1L);
    }

//    @Test
//    void deleteWithException() {
//        when(repository.existsById(1L)).thenReturn(false);
//
//        var response = service.delete(1L);
//        assertNotNull(response);
//
//        verify(repository, times(1)).deleteById(1L);
//    }

    private ContactDto getContactDto() {
        var contactDto = new ContactDto();
        contactDto.setAddress(address);
        contactDto.setId(id);
        contactDto.setPhoneNumber(phoneNumber);
        contactDto.setName(name);

        return contactDto;
    }

    private Contact getContact() {
        var contact = new Contact();
        contact.setAddress(address);
        contact.setId(id);
        contact.setPhoneNumber(phoneNumber);
        contact.setName(name);

        return contact;
    }
}