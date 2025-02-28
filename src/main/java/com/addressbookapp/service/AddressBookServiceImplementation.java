package com.addressbookapp.service;

import com.addressbookapp.model.AddressBook;
import com.addressbookapp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddressBookServiceImplementation implements AddressBookService{
    @Autowired
    AddressBookRepository addressBookRepository;

    @Override
    public List<AddressBook> getAllAddresses(){
        return addressBookRepository.findAll();
    }

    //get address by id
    @Override
    public Optional<AddressBook> getAddressById(Long id){
        return addressBookRepository.findById(id);
    }

    //save the address
    @Override
    public AddressBook addAddress(AddressBook addressBook){
        return addressBookRepository.save(addressBook);
    }

    //Update address
    @Override
    public AddressBook updateAddress(Long id, AddressBook updateAddressBook){
        Optional<AddressBook> optionalAddressBook = getAddressById(id);
        if(optionalAddressBook.isPresent()){
            AddressBook addressBook = optionalAddressBook.get();
            addressBook.setName(updateAddressBook.getName());
            addressBook.setAddress(updateAddressBook.getAddress());
            addressBook.setPhoneNo(updateAddressBook.getPhoneNo());
            addressBook.setEmail(updateAddressBook.getEmail());
            return addressBookRepository.save(addressBook);
        }
       else{
           return  null;
        }
    }

    //Delete Address
    @Override
    public String deleteAddressById(Long id){
        Optional<AddressBook> optionalAddressBook = getAddressById(id);
        if(optionalAddressBook.isPresent()) {
            addressBookRepository.deleteById(id);
            return "Address Delete Successful";
        }
        else{
            return null;
        }
    }
}
