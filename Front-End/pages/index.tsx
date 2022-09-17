import NextLink from "next/link";
import { useCallback, useEffect, useState } from "react";
import { fetchPost } from "../api/fetchPost";
import { LoadingAnimation } from "../components/LoadingAnimation";
import { NavBar } from "../components/Navbar";

export default function Home() {
  const [posts, setPosts] = useState([]);
  const [fetch, setFetch] = useState(true);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const fetchPosts = () => {
    fetchPost(page)
      .then((result) => {
        if (result.status === 200) {
          console.log(result.data);

          setPage(page + 1);
          setPosts((prev) => [...prev, ...result.data]);
          setFetch(false);
        }
      })
      .catch((error) => {
        console.error(error);
        setFetch(false);
        setHasMore(false);
      });
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
        <div>
          {posts.map((post) =>
            !post ? null : (
              <div className="flex-col	flex items-center column" key={post.id}>
                <div className="my-3 rounded-md bg-teal-100 w-2/3 grid  grid-flow-col gap-0">
                  <div className="row-span-2 col-span-2 ml-3 my-3">
                    <NextLink href="/post/[id]" as={`/post/${post.id}`}>
                      <a
                        className="text-xl  
                      font-normal font-coolx hover:text-zinc-400 "
                      >
                        {post.title}
                      </a>
                    </NextLink>
                    <h1>{post?.userId?.username}</h1>
                    <p className="mt-2">{post.text}</p>
                  </div>
                </div>
              </div>
            )
          )}
          <div className="my-3 grid">
            {hasMore ? (
              <button
                onClick={(e) => {
                  fetchPosts();
                  e.preventDefault();
                }}
                className="bg-white hover:bg-gray-100 text-gray-800 font-semibold py-2 px-4 border border-gray-400 rounded shadow m-auto"
              >
                Load More
              </button>
            ) : (
              <h1 className="m-auto text-stone-50 font-coolx 2xl:text-xl">
                No Post Left ;({" "}
              </h1>
            )}
          </div>
        </div>
      )}
    </>
  );
}
