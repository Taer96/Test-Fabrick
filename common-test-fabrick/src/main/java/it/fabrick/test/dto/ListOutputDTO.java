package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ListOutputDTO<T> implements Serializable {

	private static final long serialVersionUID = 4607242414145782118L;
	private List<T> list;
}
