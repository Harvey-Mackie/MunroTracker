package com.munro.api.repository;

import com.munro.api.model.entities.MunroCompletedKudosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunroCompletedKudosRepository extends JpaRepository<MunroCompletedKudosEntity, Long> {
}
