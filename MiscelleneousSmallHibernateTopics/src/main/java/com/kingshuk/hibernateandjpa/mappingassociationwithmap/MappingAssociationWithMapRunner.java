package com.kingshuk.hibernateandjpa.mappingassociationwithmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component("mappingWithMapWithMappedAttribute")
public class MappingAssociationWithMapRunner implements ApplicationRunner {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		
		authorRepository.save(a);

		Book b = new Book();
		b.setTitle("Hibernate Tips");
		//b.setFormat(Format.PAPERBACK);
		b.getAuthors().add(a);
		
		bookRepository.save(b);

		a.getBooks().put(b.getTitle(), b);

	}

}
