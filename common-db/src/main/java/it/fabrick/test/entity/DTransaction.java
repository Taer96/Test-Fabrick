package it.fabrick.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class DTransaction implements Serializable {

	private static final long serialVersionUID = -1022199989626406798L;
	@Id
	@Column(name = "transaction_id")
	private Long transactionId;
	@Column(name = "operation_id")
	private Long operationId;
	private String description;
	private Double amount;
	private String currency;
	@Column(name = "accounting_date")
	private Date accountingDate;
	@Column(name = "value_date")
	private Date valueDate;
}
