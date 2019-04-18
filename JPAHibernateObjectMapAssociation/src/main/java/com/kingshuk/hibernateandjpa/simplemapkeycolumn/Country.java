package com.kingshuk.hibernateandjpa.simplemapkeycolumn;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5247099140501177784L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countrySeq")
	@SequenceGenerator(name = "countrySeq", sequenceName = "COUNTRY_SEQ", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@NonNull
	@Column(name="name")
	private String name;
	
	@NonNull
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="country_id")
	@MapKeyColumn(name="id")
	private Map<Long,State> states;
}
