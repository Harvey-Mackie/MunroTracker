package com.munro.api.repository;

import com.munro.api.model.entities.MunroWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunroWeatherRepository extends JpaRepository<MunroWeatherEntity, Long> {
}
