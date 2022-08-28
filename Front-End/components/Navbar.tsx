import React, { useEffect, useState } from "react";
import NextLink from "next/link";
import { me } from "../api/me";
import { login } from "../api/login";
interface NavBarProps {}

export const NavBar: React.FC<NavBarProps> = ({}) => {
  const [res, setRes] = useState() as any;
  const [user, setUser] = useState() as any;



  useEffect(() => {
    const apiCall =  () => {
     me().then(s=>setRes(s)).catch(function (error) {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log('Error', error.message);
      }
      console.log(error.config);
    });
  

    };
    apiCall();
  }, []);
  console.log(res);
  
  
  return (
    <div>
      <nav className="bg-white border-gray-300 px-2 sm:px-4 py-2.5 dark:bg-gray-900 ">
        <div className=" flex flex-wrap justify-between">
          <div className="flex flex-col p-3 mt-auto  rounded-lg border border-transparent md:flex-row md:space-x-8  m:text-sm md:font-medium   ml-auto">
            <NextLink href={"/login"}>
              <a
                href="#"
                className="block py-2 pr-4 pl-3 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 dark:text-white"
                aria-current="page"
              >
                Login
              </a>
            </NextLink>
            <NextLink href={"/register"}>
              <a
                href="#"
                className="block py-2 pr-4 pl-3 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 dark:text-white"
                aria-current="page"
              >
                Register
              </a>
            </NextLink>
          </div>
        </div>
      </nav>
    </div>
  );
};
