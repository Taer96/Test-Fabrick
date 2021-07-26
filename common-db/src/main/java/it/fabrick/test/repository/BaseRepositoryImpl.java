package it.fabrick.test.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<T> extends SimpleJpaRepository<T, Long> implements BaseRepository<T> {

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
	}
}
