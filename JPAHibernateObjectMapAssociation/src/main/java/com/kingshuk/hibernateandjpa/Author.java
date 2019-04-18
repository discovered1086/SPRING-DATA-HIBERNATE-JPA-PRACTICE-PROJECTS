package com.kingshuk.hibernateandjpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
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
@Table(name = "AUTHOR")
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
	@JoinTable(name = "AUTHOR_BOOK_GROUP", joinColumns = {
			@JoinColumn(name = "fk_author", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_group", referencedColumnName = "id") })
	@MapKey(name = "title")
	@Builder.Default
	private Map<String, Book> books = new HashMap<>();

}
