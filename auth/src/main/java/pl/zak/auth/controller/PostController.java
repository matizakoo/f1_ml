package pl.zak.auth.controller;


import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.dialect.function.DB2SubstringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zak.auth.controller.advice.ResponseHelper;
import pl.zak.auth.dto.InfoDTO;
import pl.zak.auth.dto.PostDTO;
import pl.zak.auth.dto.UsersDTO;
import pl.zak.auth.entity.CommentEntity;
import pl.zak.auth.entity.PostEntity;
import pl.zak.auth.entity.Users;
import pl.zak.auth.mapper.PostMapper;
import pl.zak.auth.mapper.UsersMapper;
import pl.zak.auth.repository.CommentRepository;
import pl.zak.auth.repository.PostRepository;
import pl.zak.auth.repository.UsersRepository;
import pl.zak.auth.service.PostService;
import pl.zak.auth.utils.ControllerEndpoints;

import java.util.List;

@RestController
@RequestMapping(ControllerEndpoints.USER)
@AllArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private final PostMapper postMapper;
    private final CommentRepository commentRepository;
    @PostMapping(value = "/post")
    public ResponseEntity<InfoDTO> test(@RequestParam String topic,
                                        @RequestParam String email) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTopic(topic);
        postEntity.setAuthor(usersRepository.findByEmail(email).get());
        postRepository.save(postEntity);
        return ResponseHelper.response200("Dodano post");
    }

    @GetMapping(value = "/post/all")
    public ResponseEntity<List<PostDTO>> getAll() {
        return ResponseEntity.ok(postMapper.mapListOfPostToListPostDTO(postRepository.findAll()));
    }

    @GetMapping(value = "/post")
    public ResponseEntity<?> getSinglePost(@RequestParam Integer id) throws Exception {
        return ResponseEntity.ok(postMapper.mapToDTO(postRepository.findById(Integer.valueOf(id)).get()));
    }

    @PostMapping(value = "/post/comment")
    public ResponseEntity<?> addNewComment(@RequestParam String email,
                                           @RequestParam String comment,
                                           @RequestParam Integer postId
                                           ) throws Exception {
        Users users = usersRepository.findByEmail(email).get();
        PostEntity postEntity = postRepository.findById(postId).get();
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(comment);
        commentEntity.setAuthor(users);
        commentEntity.setPost(postEntity);
        commentRepository.save(commentEntity);
        return ResponseHelper.response200("Udalo sie :)");
    }
}