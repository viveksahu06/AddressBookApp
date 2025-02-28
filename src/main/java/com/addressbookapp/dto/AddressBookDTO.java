package com.addressbookapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public @Data class AddressBookDTO {
    private String name;
    private String phoneNo;
    private String email;
    private String address;
}
