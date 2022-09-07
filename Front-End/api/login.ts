import axios from "axios";

export const login = () => {
  const loginForm = {
    username: "root",
    password: "root",
  };
  axios.post("http://localhost:8080/api/login", loginForm, {
    withCredentials:true
  }).then();
};
