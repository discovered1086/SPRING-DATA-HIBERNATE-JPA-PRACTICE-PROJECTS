package com.hibernatepractice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OLD_DATE_TEST")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporalTestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENTITY_ID", updatable = false, nullable = false)
	private long entityId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OLD_DATE_TIME")
	private Date oldDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OLD_TIMESTAMP")
	private Date oldTimeStamp;

	@Temporal(TemporalType.DATE)
	@Column(name = "OLD_DATE")
	private Date oldDate;

	@Temporal(TemporalType.TIME)
	@Column(name = "OLD_TIME")
	private Date oldTime;

	@Column(name = "OLD_SQL_DATE")
	private java.sql.Date sqlDate;

	@Column(name = "OLD_SQL_DATETIME")
	private java.sql.Timestamp sqlDateTime;

	@Column(name = "OLD_SQL_TIMESTAMP")
	private java.sql.Timestamp sqlTimeStamp;

	@Column(name = "OLD_SQL_TIME")
	private java.sql.Time sqlTime;

	@Override
	public String toString() {
		return new StringBuilder().append("\n\n\n").append("The Date time values are: \n")
				.append("====================================\n").append("oldDateTime = ")
				.append(oldDateTime.toString()).append("\n").append("oldTimeStamp = ").append(oldTimeStamp.toString())
				.append("\n").append("oldDate = ").append(oldDate.toString()).append("\n").append("oldTime = ")
				.append(oldTime.toString()).append("\n").append("sqlDate = ").append(sqlDate.toString()).append("\n")
				.append("sqlDateTime = ").append(sqlDateTime.toString()).append("\n").append("sqlTimeStamp = ")
				.append(sqlTimeStamp.toString()).append("\n").append("sqlTime = ").append(sqlTime.toString())
				.append("\n").append("====================================").append("\n\n\n").toString();
	}

}
