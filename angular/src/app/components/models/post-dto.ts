import {CommentDTO} from "./comment-dto";

export interface PostDTO {
  id: number;
  topic: string;
  author: {
    id: number;
    username: string;
  };
  comments: CommentDTO[];
}
