package com.kingshuk.hibernateandjpa.mappingassociationwithmap.withoutmappedattribute;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "BOOK_WITH_MKC")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", insertable = false, updatable = false)
	private long id;

	@Version
	private int version;

	@Column(name = "BOOK_SEQ")
	private int sequenceNumber;

	private String title;

	@ManyToMany
	@JoinTable(name = "AUTHOR_BOOK_GROUP_MKC", joinColumns = {
			@JoinColumn(name = "fk_group", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_author", referencedColumnName = "id") })
	@Builder.Default
	private List<Author> authors = new ArrayList<>();
}
