import axios from "axios";
export const fetchPostById = (id: number) => {
  return axios({
    method: "get",
    url: `http://localhost:8080/api/post/post?id=${id}`,
    withCredentials: true,
  });
};
