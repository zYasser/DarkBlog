import { Formik, Form } from "formik";
import React, { useEffect, useState } from "react";
import { me } from "../api/me";
import { CustomButton } from "../components/CustomButton";
import { InputField } from "../components/InputField";
import { confirmationHandler } from "../util/confirmationHandler";
import { useRouter } from "next/router";
import { createNewPost } from "../api/CreateNewPost";

interface createPostProps {}

export const createPost: React.FC<createPostProps> = ({}) => {
  const [user, setUser] = useState() as any;
  const route = useRouter();
  const [error, setError] = useState(false);
  useEffect(() => {
    const fetchUser = () => {
      me()
        .then((result) => {
          if (result.status === 200) {
            setUser(result.data);
            console.log(user);
          } else {
            route.push("/login");
          }
        })
        .catch(() =>
          route.push({
            pathname: "/login",
            query: { from: "create-post" },
          })
        );
    };

    fetchUser();
  }, []);
  return (
    <Formik
      initialValues={{ title: "", text: "" }}
      onSubmit={(values) => {
        createNewPost(values)
          .then(() => {
            route.push("/");
          })
          .catch(() => {
            setError(true);
          });
        console.log(values);
      }}
    >
      {({ isSubmitting, resetForm }) => (
        <div className="h-screen grid content-center">
          <Form className="m-auto py-10 w-3/6 bg-white rounded-lg shadow dark:border dark:bg-gray-800 dark:border-gray-700 s:p-15">
            <div>
              <InputField name="title" label="Post Text"></InputField>
              <InputField name="text" label="Post Text" textarea></InputField>
            </div>
            <div className="flex justify-evenly">
              <CustomButton
                label={"Clear Text"}
                text={"btn-clear"}
                reset
                onClick={() => {
                  if (
                    confirmationHandler(
                      "Are You Sure You Want To Clear All Post Fields?"
                    )
                  )
                    resetForm();
                }}
              />
              <CustomButton
                type={"submit"}
                label={"Submit"}
                text={"btn-submit"}
                Submitted={isSubmitting}
              />
            </div>
          </Form>
        </div>
      )}
    </Formik>
  );
};
export default createPost;
