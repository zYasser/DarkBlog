import React, { useEffect, useState } from "react";
import NextLink from "next/link";
import { login } from "../api/login";
interface NavBarProps {}

export const NavBar: React.FC<NavBarProps> = ({}) => {
  const [res, setRes] = useState() as any;
  const [user, setUser] = useState() as any;



  useEffect(() => {
    const apiCall =  async () => {
      const res =await login({username:"root" , password:"root"})
      if(res?.status!==200){
        console.log('error' , res , '\n' ,res.data);
        
        setRes(res.data.error);
      }
      else{
        console.log('error' , res.data);
        
        setRes(res.data)
      }

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
