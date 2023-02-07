package com.munro.api.repository;

import com.munro.api.model.domain.MunroCompletedCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunroCompletedCommentRepository extends JpaRepository<MunroCompletedCommentEntity, Long> {
}
