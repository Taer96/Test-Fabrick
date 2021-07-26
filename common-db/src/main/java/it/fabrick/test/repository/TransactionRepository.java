package it.fabrick.test.repository;

import org.springframework.stereotype.Repository;

import it.fabrick.test.entity.DTransaction;

@Repository
public interface TransactionRepository extends BaseRepository<DTransaction> {

}
