package com.kingshuk.hibernateandjpa.mappingassociationwithmap.withoutmappedattribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



@Component("mappingWithMapWithOutMappedAttribute")
public class MappingAssociationWithMapRunner implements ApplicationRunner {
	
	@Autowired
	private BookWithoutMappedAttributeRepository bookRepository;
	
	@Autowired
	private AuthorWithoutMappedAttributeRepository authorRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		
		authorRepository.save(a);

		Book b = new Book();
		b.setTitle("Hibernate Tips");
		//b.setFormat(Format.PAPERBACK);
		b.setSequenceNumber(1);
		//b.getAuthors().add(a);
		
		bookRepository.save(b);
		
		Book b1 = new Book();
		b1.setTitle("Hibernate Books");
		//b.setFormat(Format.PAPERBACK);
		b1.setSequenceNumber(2);
		//b1.getAuthors().add(a);
		
		bookRepository.save(b1);

		a.getBooks().put(b.getSequenceNumber(), b);
		a.getBooks().put(b1.getSequenceNumber(), b1);

	}

}
