import axios from "axios";
var qs = require("qs");

interface LoginForm {
  username: String;
  password: String;
}

export const loginRest = (data: LoginForm) => {
  var para = qs.stringify({
    'username': data.username,
    'password': data.password 
  });
  var config = {
    method: 'post',
    url: 'http://localhost:8080/api/login',
    headers: { 
        'Access-Control-Allow-Credentials':true,

    },
    data : para
  };
return axios(config)
};
