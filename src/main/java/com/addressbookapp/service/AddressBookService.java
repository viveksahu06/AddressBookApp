package com.addressbookapp.service;

import com.addressbookapp.model.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookService {
    List<AddressBook> getAllAddresses();
    Optional<AddressBook> getAddressById(Long id);
    AddressBook addAddress(AddressBook addressBook);
    AddressBook updateAddress(Long id, AddressBook updateAddressBook);
    String deleteAddressById(Long id);
}
