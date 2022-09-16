import { NavBar } from "../components/Navbar";
import NextLink from "next/link";
import { LoadingAnimation } from "../components/LoadingAnimation";
import React, { useEffect, useState } from "react";
import { fetchPost } from "../api/fetchPost";

export default function Home() {
  const [posts, setPosts] = useState() as any;
  const [fetch, setFetch] = useState(false);
  const [count, setCount] = useState(0);
  const fetchPosts = async () => {
    setFetch(true);
    try {
      const result = await fetchPost(count);
      if (result.status === 200) {
        setCount(count + 1);
        setPosts(result.data);
      }
      setFetch(false);
    } catch (error) {
      console.error(error);
      setFetch(false);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, []);
  return (
    <>
      <NavBar />
      {fetch ? (
        <LoadingAnimation />
      ) : (
        <>
          {console.log(posts)}
          <div className="flex-col	flex items-center column">
            <div className="my-3 rounded-md bg-teal-100 w-2/3 grid  grid-flow-col gap-0">
              <div className="row-span-2 col-span-2 ml-3 my-3">
                <NextLink href="">
                  <a className="text-xl bold font-mono">Link</a>
                </NextLink>
                <h1>By root</h1>
                <p className="mt-2">
                  Lorem ipsum dolor sit, amet consectetur adipisicing elit.
                  Voluptate veritatis molestias cupiditate, quia aliquid
                  recusandae magni, asperiores vel reprehenderit odio
                  voluptatibus, quidem nesciunt praesentium fugit. Odio error
                  nesciunt totam facere!
                </p>
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
}
