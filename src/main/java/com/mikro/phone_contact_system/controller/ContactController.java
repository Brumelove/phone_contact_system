package com.mikro.phone_contact_system.controller;

import com.mikro.phone_contact_system.dto.ContactDto;
import com.mikro.phone_contact_system.exception.BadRequestException;
import com.mikro.phone_contact_system.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Brume
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/phone/contacts")
public class ContactController {
    private final ContactService service;

    @PostMapping()
    public ResponseEntity<ContactDto> addPlot(@Valid @RequestBody ContactDto request) {
        if (request == null) {
            throw new BadRequestException("request can't be null");
        }
        return ResponseEntity.ok().body(service.createContact(request));
    }



    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDto> editAPlotOfLand(@Valid @RequestBody ContactDto request, @PathVariable Long contactId) {
        return ResponseEntity.ok().body(service.updateContact(contactId, request));
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        return ResponseEntity.ok().body(service.getAllContacts());

    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable Long contactId) {
        return ResponseEntity.ok().body(service.findById(contactId));

    }
    @DeleteMapping("/{contactId}")
    public ResponseEntity<String> deleteContact( @PathVariable Long contactId) {
        return ResponseEntity.ok().body(service.delete(contactId));

    }
}
