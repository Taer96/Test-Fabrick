package it.fabrick.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="D_TRANSACTION")
@Data
public class DTransaction implements Serializable {

	private static final long serialVersionUID = -1022199989626406798L;
	@Id
	@Column(name = "transaction_id", nullable = false)
	private Long transactionId;
	@Column(name = "operation_id", nullable = false)
	private String operationId;
	private String description;
	@Column(nullable = false)
	private Double amount;
	@Column(nullable = false)
	private String currency;
	@Column(name = "accounting_date", nullable = false)
	private Date accountingDate;
	@Column(name = "value_date", nullable = false)
	private Date valueDate;
}
