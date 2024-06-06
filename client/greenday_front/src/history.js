import logo from "./logo.svg";
import "./App.css";
import Home from "./Home.js";
import Notice from "./notice.js";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";
import Pagination from "react-js-pagination";
import axios from "axios";

function History() {
  const [diary, setDiary] = useState([]);
  const [page, setPage] = useState(1);
  const [items, setItems] = useState(2);
  // const [count, setCount] = useState(0); // 총 아이템 수를 저장할 상태
  const [userInformation, setUserInformation] = useState([]);
  const [token, setToken] = useState(""); // 토큰 상태 추가

  const handlePageChange = (page) => {
    setPage(page);
  };

  const fetchDiary = async (diary_content) => {
    try {
      const response = await axios.get(`http://localhost:8080/get/history`, {
        // const data = await response.json();
        headers: {
          Authorization: `Bearer ${token}`, // 요청 시 토큰을 포함하여 보낸다
        },
      });
      const data = response.data;
      setDiary(data);
    } catch (error) {
      console.error("Error fetching diary:", error);
    }
  };

  const handleNaverLogout = () => {
    const accessToken = JSON.parse(
      localStorage.getItem("userInfo")
    )?.accessToken;

    if (!accessToken) {
      console.error("No access token found");
      return;
    }
    axios
      .post("http://localhost:8080/api/logout", { accessToken })
      .then((response) => {
        if (response.status === 200) {
          // Remove all local storage data
          localStorage.clear();

          // 네이버 로그아웃 URL을 새 창으로 열기
          const logoutWindow = window.open(
            `https://nid.naver.com/nidlogin.logout`,
            "_blank"
          );

          // 일정 시간(예: 3초) 후 새 창을 닫고 원래 창을 리다이렉트
          setTimeout(() => {
            if (logoutWindow) {
              logoutWindow.close();
            }
            // 현재 창을 http://localhost:3000/로 리다이렉트
            window.location.href = `http://localhost:3000/`;
          }, 100); // 3초 후 새 창 닫기 및 리다이렉트
        }
      })
      .catch((error) => {
        console.error("Logout error:", error);
      });
  };
  useEffect(() => {
    fetchDiary(page);
  }, [page, token]);

  //사용자아이디 받아오는 구역
  useEffect(() => {
    const userInfoFromStorage = localStorage.getItem("userInfo");
    if (userInfoFromStorage) {
      setUserInformation(JSON.parse(userInfoFromStorage));
      console.log(userInfoFromStorage); // 유저 정보를 콘솔에 출력
    }
  }, []);

  return (
    <>
      <div className="App">
        <div>
          <h5>
            {userInformation.name}님,
            <br />
            환영합니다.
            <br />
            <br />
            <div className="one"></div>
          </h5>

          <ul className="navigation-menu">
            <li>
              <Link to="/Home">홈</Link>
            </li>
            <br />
            <li>
              <Link to="/Notice">게시판</Link>
            </li>
            <br />
            <li>
              <div className="click">히스토리</div>
            </li>
            <br />
          </ul>
          <button
            className="Logout"
            onClick={() => {
              handleNaverLogout();
            }}
          >
            <img className="Logout" src="logout.png" />
          </button>

          <Routes>
            <Route path="/Home" element={<Home />}></Route>
            <Route path="/Notice" element={<Notice />}></Route>
          </Routes>
        </div>

        <h1>your history!</h1>

        <div>
          {diary
            .slice(items * (page - 1), items * (page - 1) + items)
            .map((diary, index) => (
              <div className="history" key={index}>
                {/* <div className="day">
                {diary.index}
                <br />
              </div> */}
                <div className="content">{diary.diary_content}</div>
              </div>
            ))}
        </div>

        <Pagination
          activePage={page} //현재페이지
          itemsCountPerPage={items} //한 페이지당 보여줄 리스트 아이템의 개수
          totalItemsCount={diary.length} //총 데이터 개수
          pageRangeDisplayed={5} //paginator 내에서 보여줄 페이지의 범위
          prevPageText={"‹"} // "이전"을 나타낼 텍스트(prev, <, )
          nextPageText={"›"} // "다음"을 나타낼 텍스트(next, >, )
          onChange={handlePageChange} //페이지가 바뀔 때 핸들링해줄 함수
        />
      </div>
    </>
  );
}

export default History;
