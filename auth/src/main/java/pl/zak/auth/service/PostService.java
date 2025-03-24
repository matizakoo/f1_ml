package pl.zak.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zak.auth.repository.PostRepository;

@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
}
