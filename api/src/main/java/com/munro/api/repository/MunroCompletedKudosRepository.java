package com.munro.api.repository;

import com.munro.api.model.domain.MunroCompletedKudosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunroCompletedKudosRepository extends JpaRepository<MunroCompletedKudosEntity, Long> {
}
