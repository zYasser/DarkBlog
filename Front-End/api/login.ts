import axios from "axios";


interface LoginForm{
  username:String
  password:String
}

export const login = ({username,password}) => {
  const loginForm = {
    username: "root",
    password: "root",
  };
  return axios.post("http://localhost:8080/api/login", {
    header:{
      'username':username,
      'password': password
    }
  }, {
    withCredentials:true
  }).then();
};
