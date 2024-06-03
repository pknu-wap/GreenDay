// src/main/frontend/src/setProxy.js

const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "ec2-3-36-87-184.ap-northeast-2.compute.amazonaws.com:8080", // AWS 서버 URL or localhost:설정한포트번호
      changeOrigin: true,
    })
  );
};
