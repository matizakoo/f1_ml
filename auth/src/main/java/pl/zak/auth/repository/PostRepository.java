package pl.zak.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zak.auth.controller.PostController;
import pl.zak.auth.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
