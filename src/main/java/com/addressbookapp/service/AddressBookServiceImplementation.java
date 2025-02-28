package com.addressbookapp.service;

import com.addressbookapp.dto.AddressBookDTO;
import com.addressbookapp.model.AddressBook;
import com.addressbookapp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookServiceImplementation implements AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Override
    public List<AddressBookDTO> getAllAddresses() {
        List<AddressBook> addressBooks = addressBookRepository.findAll();
        List<AddressBookDTO> addressBookDTOS = new ArrayList<>();

        for (AddressBook addressBook : addressBooks) {
            addressBookDTOS.add(convertToDTO(addressBook));
        }

        return addressBookDTOS;
    }

    // Get address by ID
    @Override
    public Optional<AddressBookDTO> getAddressById(Long id) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        if (addressBook.isPresent()) {
            return Optional.of(convertToDTO(addressBook.get()));
        }
        return Optional.empty();
    }

    // Save the address
    @Override
    public AddressBook addAddress(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = convertToEntity(addressBookDTO);
        return addressBookRepository.save(addressBook);
    }

    // Update address
    @Override
    public AddressBook updateAddress(Long id, AddressBookDTO updateAddressBook) {
        Optional<AddressBook> optionalAddressBook = addressBookRepository.findById(id);
        if (optionalAddressBook.isPresent()) {
            AddressBook addressBook = optionalAddressBook.get();
            addressBook.setName(updateAddressBook.getName());
            addressBook.setAddress(updateAddressBook.getAddress());
            addressBook.setPhoneNo(updateAddressBook.getPhoneNo());
            addressBook.setEmail(updateAddressBook.getEmail());
            return addressBookRepository.save(addressBook);
        }
        return null;
    }

    // Delete Address
    @Override
    public String deleteAddressById(Long id) {
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id);
            return "Address Delete Successful";
        }
        return "Address Not Found";
    }

    // Convert AddressBook to AddressBookDTO
    private AddressBookDTO convertToDTO(AddressBook addressBook) {
        return new AddressBookDTO(
                addressBook.getName(),
                addressBook.getPhoneNo(),
                addressBook.getEmail(),
                addressBook.getAddress()
        );
    }

    // Convert AddressBookDTO to AddressBook
    private AddressBook convertToEntity(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        addressBook.setName(addressBookDTO.getName());
        addressBook.setPhoneNo(addressBookDTO.getPhoneNo());
        addressBook.setEmail(addressBookDTO.getEmail());
        addressBook.setAddress(addressBookDTO.getAddress());
        return addressBook;
    }
}
