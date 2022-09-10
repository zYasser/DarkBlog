import { Form, Formik } from "formik";
import React, { useEffect } from "react";
import { InputField } from "../components/InputField";
import { CustomButton } from "../components/CustomButton";
import NextLink from "next/link";
import Link from "next/link";
import {loginRest} from '../api/loginApi'

interface LoginProps {}

const Login: React.FC<LoginProps> = ({}) => {
  useEffect(() => {
  
  }, []);


  return (
    <Formik
      initialValues={{ usernameOrEmail: "", password: "" }}
      onSubmit={ (values, { setErrors }) => {
        let result
        loginRest({username:values.usernameOrEmail , password:values.password}).then((res)=> {
          console.log(res);
          result=res;
          
        }).catch(e=>{
          setErrors(e)
          console.log(e.toJSON());
          
        })
        console.log(result);
        
      }}
    >
      {({ isSubmitting }) => (
        <div className="h-screen grid content-center">
        <Form className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-5 dark:bg-gray-800 dark:border-gray-700 m-auto space-y-4 md:space-y-6">
          <InputField label="Username Or Email" name="usernameOrEmail" />
          <InputField label="Password" name="password" />
          <div className="flex w-95% h-2.5">
          <NextLink href="/forget-password">
                <a className="ml-auto">Forget Password?</a>
              </NextLink>
              </div>
          <div className="flex justify-evenly">
            <CustomButton label={"Cancel"} text={"btn-cancel"} />
            <CustomButton label={"Submit"} text={"btn-submit"} />
          </div>
          
        </Form>
        </div>
      )}
    </Formik>
  );
};
export default Login;
