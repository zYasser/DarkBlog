/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: ['./pages/**/*.{js,ts,jsx,tsx}', './components/**/*.{js,ts,jsx,tsx}'],
   darkMode: false, // or 'media' or 'class'
   theme: {
    extend: {     
      fontFamily:{
          'loto':['Lato', 'sans-serif']
      } ,
      width: {
      '95%': '95%',
    }},
   },
   variants: {
     extend: {},
   },
   plugins: [],
 }
