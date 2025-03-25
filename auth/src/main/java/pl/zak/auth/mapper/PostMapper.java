package pl.zak.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pl.zak.auth.dto.PostDTO;
import pl.zak.auth.entity.PostEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = UsersMapper.class)
public interface PostMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "topic", target = "topic")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "comments", target = "comments")
    PostDTO mapToDTO(PostEntity postEntity);
    List<PostDTO> mapListOfPostToListPostDTO(List<PostEntity> postEntities);
}
