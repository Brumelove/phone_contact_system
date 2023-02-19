package com.mikro.phone_contact_system.repository;

import com.mikro.phone_contact_system.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Brume
 **/
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
