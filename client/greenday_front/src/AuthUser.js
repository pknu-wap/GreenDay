import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function AuthUser() {
  const navigate = useNavigate();

  useEffect(() => {
    // 네이버 로그아웃이 완료되었음을 확인하고 Xlog로 리다이렉트
    navigate("/Xlog");
  }, [navigate]);

  return (
    <div>
      <h1>Logging out...</h1>
    </div>
  );
}

export default AuthUser;
