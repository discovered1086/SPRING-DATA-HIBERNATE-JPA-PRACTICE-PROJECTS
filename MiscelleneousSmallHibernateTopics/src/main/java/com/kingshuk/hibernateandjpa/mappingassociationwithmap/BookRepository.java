package com.kingshuk.hibernateandjpa.mappingassociationwithmap;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
