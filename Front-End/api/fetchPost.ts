import axios from "axios";

export const fetchPost = (page: number) => {

  return axios({
    method: "get",
    url: `http://localhost:8080/api/post/posts?page=${page}`,
    withCredentials: true,
  });
};
