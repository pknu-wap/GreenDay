const config = {
    development: {
      apiUrl: "http://localhost:5000/api",
    },
    production: {
      apiUrl: "http://3.36.87.184/api"
    },
  };
  
  module.exports = config[process.env.NODE_ENV || 'development'];