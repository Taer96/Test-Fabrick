package it.fabrick.test.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="D_ACCOUNT")
@Data
public class DAccount implements Serializable {

	private static final long serialVersionUID = 318766729078289247L;
	@Id
	@Column(name = "account_id", nullable = false)
	private Long accountId;
	@OneToMany(mappedBy = "account")
	private List<DTransaction> transactions; 
}
