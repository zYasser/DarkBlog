import React, { useEffect, useState } from "react";
import { NavBar } from "../../components/Navbar";
import { useRouter } from "next/router";
import { fetchPostById } from "../../api/fetchPostById";
import { LoadingAnimation } from "../../components/LoadingAnimation";
import { ErrorField } from "../../components/ErrorField";

interface PostProps {}

export const Post: React.FC<PostProps> = ({}) => {
  const router = useRouter();
  const [post, setPost] = useState() as any;
  const [fetching, setFetching] = useState(true);
  const [errorMsg, setError] = useState("");
  const { id } = router.query;

  useEffect(() => {
    const getPost = async () => {
      if (!id) return;

      try {
        const result = await fetchPostById(parseInt(id as string));
        if (result.status === 200) {
          setPost(result.data);
          setFetching(false);
        }
      } catch (error) {
        if (error.response.data.message) setError(error.response.data.message);
        else {
          setError("Something went wrong Please Try Again");
        }
        setFetching(false);
      }
    };
    getPost();
  }, [id]);
  if (fetching) {
    return <LoadingAnimation />;
  } else if (errorMsg) {
    return (
      <div className="items-center flex-col	flex">
        <div className=" w-1/4">
          <ErrorField message={errorMsg}></ErrorField>
        </div>
      </div>
    );
  } else
    return (
      <div>
        {post ? (
          <div className="w-4/5 items-center m-auto my-10 border-solid bg-white p-2 rounded-md shadow-xl">
            <div className="my-4 ">
              <h1 className="my-4">{post?.title}</h1>
              <h2>by {post?.userId?.username}</h2>
            </div>
            <p>{post?.text}</p>
          </div>
        ) : null}
      </div>
    );
};
export default Post;
