import React, { useEffect, useState } from "react";
import NextLink from "next/link";
import { me } from "../api/me";
interface NavBarProps {}

export const NavBar: React.FC<NavBarProps> = ({}) => {
  const [user, setUser] = useState() as any;
  useEffect(() => {
    const fetchUser = async () => {
      const result = await me();
      if (result.status === 200) {
        setUser(result.data);
        console.log(user);
      }
    };
    fetchUser();
  }, []);

  return (
    <div className="sticky top-0">
      <nav className="bg-white border-gray-300 px-2 sm:px-4 py-2.5 dark:bg-gray-900">
        <div className=" flex flex-wrap justify-between">
          <div className="flex flex-col p-3 mt-auto  rounded-lg border border-transparent md:flex-row md:space-x-8  m:text-sm md:font-medium   ml-auto">
            {user ? (
              <>
                <h2 className="text-stone-50 font-loto 2xl:text-xl">{user.username}</h2>
              </>
            ) : (
              <>
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
              </>
            )}
          </div>
        </div>
      </nav>
    </div>
  );
};
