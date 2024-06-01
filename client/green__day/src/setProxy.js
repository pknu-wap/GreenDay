// src/main/frontend/src/setProxy.js

const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "http://3.36.87.184:8080", // AWS 서버 URL or localhost:설정한포트번호
      changeOrigin: true,
    })
  );
};
