package it.fabrick.test.repository;

import org.springframework.stereotype.Repository;

import it.fabrick.test.entity.DAccount;

@Repository
public interface AccountRepository extends BaseRepository<DAccount> {

}
