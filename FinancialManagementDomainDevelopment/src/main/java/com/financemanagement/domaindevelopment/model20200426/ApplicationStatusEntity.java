package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.newenums.ApplicationStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "applicationError")
@Table(name = "APPLICATION_STATUS_MASTER")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class ApplicationStatusEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 995642617560236207L;
	
	@Id
	@Column(name="STTS_CD", length = 20, nullable = false)
	@NotBlank(message = "Status code value can't be blank")
	private String statusCode;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="STTS_TYP", length = 50)
	@NotNull(message = "Status type value can't be blank")
	private ApplicationStatusType typeOfStatus;
	
	@Column(name = "STTS_NTS", length = 1000)
	@NotBlank(message = "Status notes value can't be blank")
	private String statusNotes;
	
	@Column(name = "STTS_MSG", length = 400)
	@NotBlank(message = "Status message value can't be blank")
	private String statusMessage;
	
	@Column(name = "STTS_TYP_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime statusTypeEffectiveDate;

	@Column(name = "STTS_TYP_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime statusTypeTerminationDate;

}
