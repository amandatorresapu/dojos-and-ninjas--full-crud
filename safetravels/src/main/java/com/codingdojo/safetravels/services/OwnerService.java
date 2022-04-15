package com.codingdojo.safetravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.safetravels.models.Owner;
import com.codingdojo.safetravels.repositories.OwnerRepository;

@Service
public class OwnerService {
	
	private final OwnerRepository ownerRepository;

	public OwnerService(OwnerRepository ownerRepository) {
		
		this.ownerRepository = ownerRepository;
	}
	
	
	//Create and owner
	public Owner createOwner(Owner owner) {
		return ownerRepository.save(owner);
	}
	
	//Find an owner
	public Owner findOwner(Long id) {
    	Optional<Owner> oneOwner = ownerRepository.findById(id);
    	if(oneOwner.isPresent()) {
    		return oneOwner.get();
    	} else {
    		return null;
    	}
    	
    	
    }
	
//	Find all the owners
	public List<Owner> allOwners(){
		return ownerRepository.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	

}
