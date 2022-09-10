import { Field } from "formik";
import React from "react";
interface InputFieldProps {
  label: string;
  name: string;
}

export const InputField: React.FC<InputFieldProps> = ({ label, name }) => {
  return (
    <div className="p-4">
      <label
        htmlFor={label}
        className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
      >
        {label}
      </label>
      <Field
        type={name === "password" ? "password" : null}
        name={name}
        id={name}
        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        placeholder={label}
        required={true}
      />
    </div>
  );
};
