package com.kingshuk.hibernateandjpa.simplemapkeycolumn;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder()
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STATE")
public class State implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3857373683862528223L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stateSeq")
	@SequenceGenerator(name = "stateSeq", sequenceName = "STATE_SEQ", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@NonNull
	@Column(name = "name")
	private String name;
}
