package com.munro.api.repository;

import com.munro.api.model.entities.MunroCompletedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunroCompletedRepository extends JpaRepository<MunroCompletedEntity, Long> {
}
