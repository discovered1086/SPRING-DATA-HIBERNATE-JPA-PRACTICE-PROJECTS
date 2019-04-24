package com.kingshuk.hibernateandjpa.mappingwithenumandembeddable;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "AUTHOR_WITH_ENUM")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorSeq")
	@SequenceGenerator(name = "authorSeq", sequenceName = "AUTHOR_SEQ", allocationSize = 1)
	@Column(name = "id", insertable = false, updatable = false)
	private long id;
	
	@Version
	private int version;

	@NonNull
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NonNull
	@Column(name = "LAST_NAME")
	private String lastName;

	//@Embedded 
	@NonNull
	@ElementCollection
	@CollectionTable(name = "AUTHOR_ADDRESS", joinColumns = 
			{@JoinColumn(name = "AUTHOR_ID" ,referencedColumnName = "ID")})
	@MapKeyColumn(name = "ADDRESS_TYPE")
	@MapKeyEnumerated(EnumType.STRING)
	@Builder.Default
	private Map<AuthorAddressType, Address> addressMap = new HashMap<>();
}
