package pl.zak.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zak.auth.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
