import React from "react";

interface CustomButtonProps {
  label: string;
  text: string;
}

export const CustomButton: React.FC<CustomButtonProps> = ({ label, text }) => {
  return (
    <button
      id={text}
      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 border border-blue-700 rounded my-3"
    >
      {label}
    </button>
  );
};
