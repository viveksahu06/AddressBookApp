package com.addressbookapp.controller;

import com.addressbookapp.model.AddressBook;
import com.addressbookapp.service.AddressBookService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    AddressBookService addressBookService;

    //To get all the addresses stored in the database
    @GetMapping
    public List<AddressBook> getAllAddress(){
        logger.info("All address endpoint called ");
        return addressBookService.getAllAddresses();
    }

    //To get the address by id
    @GetMapping("/get/{id}")
    public Optional<AddressBook> getAddressById(@PathVariable Long id){
        logger.info("By id address endpoint called ");
        return addressBookService.getAddressById(id);
    }

    //To store the new address in database
    @PostMapping("/create")
    public AddressBook saveAddress(@RequestBody AddressBook addressBook){
        logger.info("Create address endpoint called ");
        return addressBookService.addAddress(addressBook);
    }

    //To update the existing address in the database
    @PutMapping("/update/{id}")
    public AddressBook updateAddress(@PathVariable Long id,@RequestBody AddressBook addressBook){
        logger.info("update address end point called");
        return addressBookService.updateAddress(id,addressBook);
    }

    //To delete the address from db
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id){
        logger.info("Delete address end point called");
        return addressBookService.deleteAddressById(id);
    }

}
