package it.fabrick.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fabrick.test.entity.DTransaction;

public interface TransactionRepository extends JpaRepository<DTransaction, Long> {

}
