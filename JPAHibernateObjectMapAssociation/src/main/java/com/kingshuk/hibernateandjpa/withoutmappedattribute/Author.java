package com.kingshuk.hibernateandjpa.withoutmappedattribute;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHOR_WITH_MKC")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", insertable = false, updatable = false)
	private long id;
	
	@Version
	private int version;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@ManyToMany
	@JoinTable(name = "AUTHOR_BOOK_GROUP_MKC", joinColumns = {
			@JoinColumn(name = "fk_author", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_group", referencedColumnName = "id") })
	@MapKey(name = "sequenceNumber")
//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(name="AUTHOR_BOOK_GROUP_MKC")
//	@Column(name="AUTHOR_ID")
//	@MapKeyColumn(name="BOOK_SEQ",table = "BOOK_WITH_MKC")
	@Builder.Default
	private Map<Integer, Book> books = new HashMap<>();

}
