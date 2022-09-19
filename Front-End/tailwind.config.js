/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: ['./pages/**/*.{js,ts,jsx,tsx}', './components/**/*.{js,ts,jsx,tsx}'],
   darkMode: false, // or 'media' or 'class'
   theme: {
    extend: {  
      backgroundImage:{
        "darkAndBlue":'linear-gradient(to left top, #051937, #002453, #002e70, #00398e, #0043ac);'
      },   
      fontFamily:{
        coolx:'Pacifico, cursive'
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
