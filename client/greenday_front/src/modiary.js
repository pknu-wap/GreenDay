import { useNavigate, Route, Routes} from "react-router-dom";
import React, { useState, useEffect } from 'react';
import './modiary.css'; 
import axios from "axios"; 

    const Modal = ({ isOpen, onClose, onSubmit }) => {
        const [text, setText] = useState(''); //text가 값 추적하고 setText함수 통해 상태
        const [message, setMessage] = useState('');
        const [length, setLength] = useState(0);
        const [placeholder, setPlaceholder] = useState('');
        const [title, setTitle] = useState('오늘의 그린일기');
        const [inputCount, setInputCount] = useState(0);
        const [diary_content, setDiaryContent] = useState('');

    const proverbs = [
        "일회용품", "재활용", "물", "전기", "배달", "걷기", "카페-텀블러", "분리수거", "제로웨이스트쇼핑", "친환경", 
        "멸종위기", "에코백", "설거지", "자전거", "대중교통", "미세먼지", "식물키우기", "손빨래", 
        "플로깅", "봉사활동", "포장", "헌옷수거"
        ];

    const clearTextarea = () => {
        setDiaryContent(''); //글을 지워주는 기능
    }

    const getRandomIndex = (length) => {
        return Math.floor(Math.random() * length)
    };

    useEffect(() => {
        if (isOpen) {
        setPlaceholder(proverbs[getRandomIndex(proverbs.length)]);
        setTitle(proverbs[getRandomIndex(proverbs.length)]);
        }
    }, [isOpen]); 

    //여기부터 수정---------------------------------------------------------------
    let onChange = (event) => {
        const value = event.target.value;
        setDiaryContent(event.target.value);
        setLength(event.target.value.length);
      };

    
    const handleSubmit = async (event) => { //입력 필드에서 입력이 변경될 때마다 호출되며, 입력된 값을 setText를 통해 상태 text에 저장
        event.preventDefault(); // 기본 폼 제출 동작 방지      

        console.log("텍스트내용:", diary_content);
        
        try {
            const jwtToken = localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).jwtToken : null;
            if (!jwtToken) { // JWT 토큰이 없으면 처리
                console.error("JWT 토큰이 없습니다.");
                return;
            }
    
            const response = await fetch('http://localhost:8080/post/write_diary', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${jwtToken}`
                },
                body: JSON.stringify({ diary_content }), // content 상태 값을 JSON으로 변환하여 요청 본문에 포함
            });
    
            if (!response.ok) {
                // 요청이 성공하지 않았을 때 처리
                console.error("요청 실패:", response.statusText);
                return;
            }

            const data = await response.json(); // 응답 데이터를 받아와서 data 변수에 할당
            setMessage('저장됐습니다.');
            // onSubmit(text); // 글 등록 버튼 클릭 시 동작
            alert("저장됐습니다."); // 요청 성공 시 처리
            console.log("응답 데이터:", data);

            //폼 초기화
            setDiaryContent(''); // 텍스트 입력칸을 초기화=> 다음 입력위해 빈필드갖게함
            setLength(0);
            onClose(); //저장하면 하로 닫기게함 지체ㄴㄴ
            
        } catch (error) {
            console.error("API 요청 오류:", error);
    }
}

    if (!isOpen) {
        return null; // 모달이 닫혀 있을 때는 렌더링하지 않음
    }

    async function greenDiary() {
        const REACT_APP_API = 'http://localhost:8080/post/write_diary';
        try {
            const urlParams = new URLSearchParams(window.location.search);
            const code = urlParams.get('code');
            const state = urlParams.get('state');
        
            const res = await axios.post(REACT_APP_API + `/api/user-info`, { code, state });
            const { accessToken, refreshToken, diary_id, diary_content, login_id, jwtToken } = res.data;
        
            const userInfo = {
              diary_id,
              diary_content,
              login_id,
              accessToken,
              refreshToken,
              jwtToken // jwtToken 추가
            };

            localStorage.setItem('userInfo', JSON.stringify(userInfo));
    
            // 리디렉션 없이 알림만 표시
            alert("저장됐습니다.");
        
            // 리디렉션을 위해 필요한 코드
            window.location.href = "/home";
        } catch (error) {
            console.error("오류:", error);
        }
    }

    return (
            <div className="modal">
                    <button className="Close-button" onClick={() => {onClose(); clearTextarea();}} type="button">
                        close
                    </button>
                    <h3>🌞 오늘의 주제  {'<'}{title}{'>'} 🌞</h3>
                    <div style={{ display: isOpen ? 'block' : 'none' }}> //isOpen이'block'이면 요소가 보이고, 'none'이면 요소가 보이지 않음
                    <form onSubmit={handleSubmit}>
                        <textarea 
                            className ="diarytextarea"
                            maxLength={199}
                            // maxLength="200" alert="200자가 다 채워졌습니다."
                            value={diary_content}
                            onChange={onChange} // 입력된 텍스트 변경 시 상태 업데이트
                            placeholder="오늘의 일기를 작성하세요. 일기 작성 후 사과를 눌러 일기를 저장해주세요!"
                            style={{ whiteSpace: "pre-wrap" }}
                        ></textarea>
                        <div className="diaryinputLength">{length}/200자</div>
                        <button className="Save-button" type="submit">
                            <img src='apple.png' alt="Save" />
                        </button>
                        </form>
                        </div>
                        {message && <p>{message}</p>}
             </div>
    );
};


export default Modal;