package com.financemanagement.domaindevelopment.allmodels.model.newmodels;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.newenums.ApplicationStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "applicationError")
@Table(name = "APPLICATION_ERROR_MASTER")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class ApplicationErrorEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 995642617560236207L;
	
	@Id
	@Column(name="ERR_CD", length = 10, nullable = false)
	@NotBlank(message = "Error code value can't be blank")
	private String errorCode;
	
	@Column(name="HTTP_ERR_CD", length = 10)
	@NotNull(message = "HTTP Error code value can't be blank")
	private String httpErrorCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ERR_TYP", length = 20)
	@NotNull(message = "Error code value can't be blank")
	private ApplicationStatusType typeOfError;
	
	@Column(name = "ERR_TYP_DFNTN", length = 400)
	@NotBlank(message = "Error definition value can't be blank")
	private String errorDefinition;
	
	@Column(name = "ERR_MSG", length = 400)
	@NotBlank(message = "Error message value can't be blank")
	private String errorMessage;
	
	@Column(name = "ERR_TYP_EFFCTV_DT")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	@FutureOrPresent(message = "Error code effective date can't be in the past")
	private ZonedDateTime errorTypeEffectiveDate;

	@Column(name = "ERR_TYP_TRMNTN_DT")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime errorTypeTerminationDate;

}
