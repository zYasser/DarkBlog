import { Form, Formik } from "formik";
import NextLink from "next/link";
import { useRouter } from "next/router";
import React, { useState } from "react";
import { loginRest } from "../api/loginApi";
import { CustomButton } from "../components/CustomButton";
import { ErrorField } from "../components/ErrorField";
import { InputField } from "../components/InputField";
interface LoginProps {}
const Login: React.FC<LoginProps> = ({}) => {
  const [errorMsg, setErrorMsg] = useState("");
  const route = useRouter();
  const {from} = route.query 
  console.log(from);
  
  return (
    <Formik
      initialValues={{ usernameOrEmail: "", password: "" }}
      onSubmit={(values, { setSubmitting }) => {
        
        let result;
        loginRest({
          username: values.usernameOrEmail,
          password: values.password,
        })
          .then((res) => {
            if (res.status === 200) {
              if(from){
                route.push(from as string);
              }
              else{
                route.push('/')
              }
              
            }

            setSubmitting(false);
          })
          .catch((error) => {
            setSubmitting(false)
            setErrorMsg("Something went wrong!, Please Try Again");
            if (error.response.status === 403) {
              setErrorMsg("Please Check your Username and Password!");
            } else {
              setErrorMsg("Something went wrong!, Please Try Again");
            }
          });
      }}
    >
      {({ isSubmitting }) => (
        <div className="h-screen grid content-center ">
          <Form className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-5 dark:bg-gray-800 dark:border-gray-700 m-auto space-y-4 md:space-y-6">
            <div className="sm:m-3">
              {errorMsg ? <ErrorField message={errorMsg} /> : null}
              <InputField label="Username Or Email" name="usernameOrEmail" />
              <InputField label="Password" name="password" />
            </div>
            <div className="flex w-95% h-2.5">
              <NextLink href="/forget-password">
                <a className="ml-auto">Forget Password?</a>
              </NextLink>
            </div>
            <div className="flex justify-evenly">
              <CustomButton
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
export default Login;
