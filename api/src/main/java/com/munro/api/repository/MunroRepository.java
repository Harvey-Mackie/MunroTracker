package com.munro.api.repository;

import com.munro.api.model.entities.MunroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunroRepository extends JpaRepository<MunroEntity, Long> {
}
