/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.html',
    './src/**/*.js',
    './src/**/*.css',
    './src/**/*.scss',
    './node_modules/preline/dist/*.js',
  ],
  plugins: [
    require('preline/plugin'),
  ],
  theme: {
    extend: {
      height: {
        'screen-85': '85vh',
      },
      backgroundImage: {
        'neon-color': 'linear-gradient(81.02deg, #fa5560 -23.47%, #b14bf4 45.52%, #4d91ff 114.8%)',
      },
      textColor: {
        'linkedin': '#0072b',
      }
    },
  },
}

