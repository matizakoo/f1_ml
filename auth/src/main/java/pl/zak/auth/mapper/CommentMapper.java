package pl.zak.auth.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.zak.auth.dto.CommentDTO;
import pl.zak.auth.entity.CommentEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = UsersMapper.class)
public interface CommentMapper {
    CommentDTO toDto(CommentEntity comment);
    List<CommentDTO> toDtoList(List<CommentEntity> comments);
}
