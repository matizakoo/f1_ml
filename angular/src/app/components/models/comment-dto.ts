export interface CommentDTO {
  id: number;
  content: string;
  author: {
    id: number;
    username: string;
  };
}
