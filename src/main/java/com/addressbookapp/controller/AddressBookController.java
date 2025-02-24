package com.addressbookapp.controller;

import com.addressbookapp.model.AddressBook;
import com.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    @Autowired
    AddressBookService addressBookService;

    //To get all the addresses stored in the database
    @GetMapping
    public List<AddressBook> getAllAddress(){
        return addressBookService.getAllAddresses();
    }

    //To get the address by id
    @GetMapping("/get/{id}")
    public Optional<AddressBook> getAddressById(@PathVariable Long id){
        return addressBookService.getAddressById(id);
    }

    //To store the new address in database
    @PostMapping("/create")
    public AddressBook saveAddress(@RequestBody AddressBook addressBook){
        return addressBookService.addAddress(addressBook);
    }

    //To update the existing address in the database
    @PutMapping("/update/{id}")
    public AddressBook updateAddress(@PathVariable Long id,@RequestBody AddressBook addressBook){
        return addressBookService.updateAddress(id,addressBook);
    }

    //To delete the address from db
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id){
        return addressBookService.deleteAddressById(id);
    }

}
