/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.html',
    './src/**/*.js',
    './src/**/*.css',
    './src/**/*.scss',
  ],
  theme: {
    extend: {
      height: {
        'screen-85': '85vh',
      },
      backgroundImage: {
        'neon-color': 'linear-gradient(81.02deg, #fa5560 -23.47%, #b14bf4 45.52%, #4d91ff 114.8%)',
      },
    },
  },
  plugins: [],
}

