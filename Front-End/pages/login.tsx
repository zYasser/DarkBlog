import { Form, Formik } from "formik";
import React from "react";
import { InputField } from "../components/InputField";
import { CustomButton } from "../components/CustomButton";
import NextLink from "next/link";
import Link from "next/link";

interface LoginProps {}

const Login: React.FC<LoginProps> = ({}) => {
  return (
    <Formik
      initialValues={{ usernameOrLogin: "", password: "" }}
      onSubmit={async (values, { setErrors }) => {}}
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
