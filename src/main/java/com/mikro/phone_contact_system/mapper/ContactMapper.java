package com.mikro.phone_contact_system.mapper;

import com.mikro.phone_contact_system.dto.ContactDto;
import com.mikro.phone_contact_system.model.Contact;
import org.mapstruct.*;

import java.util.List;

/**
 * @author Brume
 **/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ContactMapper {

    ContactDto mapModelToDto(Contact target);

    List<ContactDto> mapModelsToDtos(List<Contact> targets);
    Contact mapDtoToModel(ContactDto target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact partialUpdate(ContactDto contactDto, @MappingTarget Contact contact);

}
