import axios from 'axios'
interface postData {
    title: string;
    text: string;
  }
  
export const createNewPost= (_data:postData)=> {

     return axios({
     method: 'post',
     url: 'http://localhost:8080/api/post/create-post',
     withCredentials: true,
     data:{
        "post":_data
     }
   });
};