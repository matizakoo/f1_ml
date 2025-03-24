import {UsersDTO} from "./users-dto";

export interface PostDTO {
  id: number;
  topic: string;
  allowedUsers: UsersDTO[];
  author: UsersDTO;
}
