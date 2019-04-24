package com.kingshuk.hibernateandjpa.mappingwithenumandembeddable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingWithEnumAndEmbeddableHelper {

	public static void main(String[] args) {
		
		//Load the object to memory from the json file
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<AuthorAddressType, Address> addressMap = new HashMap<>();
		
		Address address = new Address("771 Shady Grove Ln",
									  "", 
									  "Buffalo Grove", 
									  "IL", 
									  "60089");
		
		Address address2 = new Address("600 Asylum Avenue",
				  "Apt - 316", 
				  "Hartford", 
				  "CT", 
				  "06105");
		
		Address address3 = new Address("2 Park Pl",
				  "Apt - 16C", 
				  "Hartford", 
				  "CT", 
				  "061056");
		
		Address address4 = new Address("30 Edwards St",
				  "", 
				  "Manchester", 
				  "CT", 
				  "06106");
		
		addressMap.put(AuthorAddressType.PRIVATE, address);
		addressMap.put(AuthorAddressType.BUSINESS, address2);
		addressMap.put(AuthorAddressType.SUMMER, address3);
		addressMap.put(AuthorAddressType.LEISURE, address4);
		
		
		Author author = new Author("Kingshuk", "Mukherjee", addressMap);
		
		try {
			objectMapper.writeValue(new File("jsonfiles/AuthorAddressInitial.json"), author);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
