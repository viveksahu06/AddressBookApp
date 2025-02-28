package com.addressbookapp.controller;

import com.addressbookapp.dto.AddressBookDTO;
import com.addressbookapp.model.AddressBook;
import com.addressbookapp.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    private AddressBookService addressBookService;

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressBookDTO>> getAllAddress() {
        logger.info("All address endpoint called");
        List<AddressBookDTO> addresses = addressBookService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    // Get address by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<AddressBookDTO> getAddressById(@PathVariable Long id) {
        logger.info("By id address endpoint called");
        Optional<AddressBookDTO> addressDTO = addressBookService.getAddressById(id);
        return addressDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new address
    @PostMapping("/create")
    public ResponseEntity<AddressBook> saveAddress(@RequestBody AddressBookDTO addressBookDTO) {
        logger.info("Create address endpoint called");
        AddressBook savedAddress = addressBookService.addAddress(addressBookDTO);
        return ResponseEntity.ok(savedAddress);
    }

    // Update an existing address
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBook> updateAddress(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        logger.info("Update address endpoint called");
        AddressBook updatedAddress = addressBookService.updateAddress(id, addressBookDTO);
        return updatedAddress != null ? ResponseEntity.ok(updatedAddress)
                : ResponseEntity.notFound().build();
    }

    // Delete an address
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        logger.info("Delete address endpoint called");
        String response = addressBookService.deleteAddressById(id);
        return response.equals("Address Delete Successful") ? ResponseEntity.ok(response)
                : ResponseEntity.notFound().build();
    }
}
