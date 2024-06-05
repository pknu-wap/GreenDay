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
  const [count, setCount] = useState(0); // 총 아이템 수를 저장할 상태
  const [userInformation, setUserInformation] = useState([]);
  const [token, setToken] = useState(""); // 토큰 상태 추가

    // const handlePageChange = (page) => {
    //   setPage(page);
    //   console.log(page);
    // };
    // 위에꺼 안되면 밑에껄로 실행해보기 
    const handlePageChange = (pageNumber) => {
      setPage(pageNumber);
    };
  
    const fetchDiary = async (pageNumber) => {
      try {
        const response = await axios.get(`https://codingapple1.github.io/shop/data2.json`,{
        // const data = await response.json();
          headers: {
            Authorization: `Bearer ${token}` // 요청 시 토큰을 포함하여 보낸다
          }
        });
        const data = response.data;
        // setDiary(data.items || []);
        // setCount(data.totalCount || 0);
        setDiary(data.items); //백에서 설정한 이름으로 바꿔야함. 데이터를 items로 가정해줌
        setCount(data.totalCount); //백에서 설정한 이름으로 바꿔야함. 총 데이터 수를 totalCount로 가정
      } catch (error) {
        console.error(  "Error fetching diary:", error);
      }
    };

    useEffect(() => {
      fetchDiary(page);
  }, [page]);

    //사용자아이디 받아오는 구역
    useEffect(() => {
      const userInfoFromStorage = localStorage.getItem('userInfo');
      if (userInfoFromStorage) {
        setUserInformation(JSON.parse(userInfoFromStorage));
        console.log(userInfoFromStorage); // 유저 정보를 콘솔에 출력
      }
    }, []);
// 효원 언니 최고 ><><><
    
  
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

          <Routes>
            <Route path="/Home" element={<Home />}></Route>
            <Route path="/Notice" element={<Notice />}></Route>
          </Routes>
        </div>

        <h1>your history!</h1>

        <div>
          {diary.map((diary, index) => (
            <div className="history" key={index}>
              <div className="day">
                {diary.index}
                <br />
              </div>
              <div className="content">{diary.content}</div>
              {/* <li key={index}>{diary.id}</li>
              <li key={index}>{diary.title}</li><br /> */}
            </div>
          ))}
        </div>

        <Pagination
              activePage={page} //현재페이지
              itemsCountPerPage={2} //한 페이지당 보여줄 리스트 아이템의 개수
              // totalItemsCount={count} //총 데이터 개수
              totalItemsCount={14} //총 데이터 개수
              pageRangeDisplayed={10} //paginator 내에서 보여줄 페이지의 범위
              prevPageText={"‹"} // "이전"을 나타낼 텍스트(prev, <, )
              nextPageText={"›"} // "다음"을 나타낼 텍스트(next, >, )
              onChange={handlePageChange} //페이지가 바뀔 때 핸들링해줄 함수
            />  
      </div>
    </>
  );
}

export default History;