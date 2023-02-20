package com.mikro.phone_contact_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Brume
 **/
@Getter @Setter
public class ContactDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "phoneNumber cannot be empty")
    private String phoneNumber;
    @NotBlank(message = "address cannot be empty")
    private String address;
}
