import axios from "axios";

export const me =async ()=> {
    
    return await axios({
        method: "get",
        url: "http://localhost:8080/api/me",
        withCredentials:true
    });
    
  
    
    }

  
