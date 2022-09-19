import axios from "axios";

export const me = () => {
  return axios({
    method: "get",
    url: "http://localhost:8080/api/me",
    withCredentials: true,
    headers: {
      "Access-Control-Allow-Credentials": true,
    },
  });
};
