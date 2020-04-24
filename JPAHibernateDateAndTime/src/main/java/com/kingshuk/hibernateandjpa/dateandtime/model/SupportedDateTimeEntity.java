package com.kingshuk.hibernateandjpa.dateandtime.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUPPORTED_DATE_TIME")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportedDateTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ENTITY_ID", updatable = false, nullable = false)
	private long entityId;

	@Column(name = "LOCAL_DATE")
	private LocalDate localDate;

	@Column(name = "LOCAL_TIME")
	private LocalTime localTime;

	@Column(name = "LOCAL_DATE_TIME")
	private LocalDateTime localDateTime;

	@Column(name = "OFFSET_TIME")
	private OffsetTime offsetTime;

	@Column(name = "OFFSET_DATE_TIME")
	private OffsetDateTime offsetDateTime;

	@Column(name = "ZONED_DATE_TIME")
	private ZonedDateTime zonedDateTime;

	public String toString() {
		StringBuilder builder = new StringBuilder();

		return builder.append("======================================================").append("\n")
				.append("Local date: ").append(this.localDate).append("\n").append("Local time: ")
				.append(this.localTime).append("\n").append("Local date time: ").append(this.localDateTime).append("\n")
				.append("Offset date time: ").append(this.offsetDateTime).append("\n").append("Offset time: ")
				.append(this.offsetTime).append("\n").append("Zoned date time: ").append(this.zonedDateTime)
				.append("\n").append("Local date time at Kolkata: ")
				.append(this.localDateTime.atZone(this.zonedDateTime.getZone()).toLocalDateTime())
				.append("\n").append("Offset date time at Kolkata: ")
				.append(this.offsetDateTime.atZoneSameInstant(ZoneId.of("Asia/Kolkata")).toOffsetDateTime()).append("\n")
				.append("Zoned date time at Kolkata: ")
				.append(this.zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"))).append("\n")
				.append("====================================================").toString();
	}
}
