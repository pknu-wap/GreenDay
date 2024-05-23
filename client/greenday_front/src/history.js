import logo from './logo.svg';
import './App.css';
import {Routes, Route,Link, useNavigate} from "react-router-dom";
import Modal from './modiary.js';
import Home from "./Home.js";
import Notice from "./Notice.js";
import React, {useEffect, useState} from "react"
import axios from "axios"



function History() {

  // let[diary, setDiary] = useState(date)
  let[diary, setDiary] = useState()
  let navigate = useNavigate();

  // const HistoryList = () => {
  //   const [HistoryList, setHistoryList] = useState([]);

  //   const getBoardList = async () => {
  //     const resp = (await axios.get('//localhost:8080/modiary')).data
  //     setHistoryList(resp.date)

  //     const pngn = resp.pagination;
  //     console.log(HistoryList);
  //   }

    return (
      <>
      <div className="App">
        <div>
          <h5>
             방문자님,<br />
            환영합니다.<br /><br />
            <div className="one"></div>
          </h5>
          
          <ul className="navigation-menu">
            <li><Link to="/Home">홈</Link></li><br />
            <li><Link to="/Notice">게시판</Link></li><br />
            <li><div className="click">히스토리</div></li><br />
          </ul>
          
          <Routes>
            <Route path="/Home" element={<Home />}></Route>
            <Route path="/Notice" element={<Notice />}></Route>
          </Routes>
        </div>
  
        <h1>
          your history!
        </h1>
        </div>

        
        {/* {diary.map((a, i) => {
            return <Card diary ={i} key={i}></Card>
          })} */}


        
  
        {/* <button onClick={() => {
                        axios.get('url주소적기') //데이터 요청하는 백 url넣어야함 
                        .then((결과) => {
                            console.log(결과.data)
                            let copy = [...diary, ...결과.data]; //리스트 [] 벗기고 {}만 정렬해줌
                            setDiary(copy); 
                        })
                        .catch (() => {
                            console.log(결과.data)
                        })
                    }   

                    } >
        자료 받기 
        </button>               */}
      </>
    );
  }

  



export default History;