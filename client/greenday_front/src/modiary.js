import { useNavigate, Route, Routes} from "react-router-dom";
import React, { useState, useEffect } from 'react';
import './modiary.css'; 
import axios from 'axios';


    const Modal = ({ isOpen, onClose, onSubmit }) => {
        const [text, setText] = useState(''); //text가 값 추적하고 setText함수 통해 상태
        const [message, setMessage] = useState('');
        const [length, setLength] = useState(0);
        const [placeholder, setPlaceholder] = useState('');
        const [title, setTitle] = useState('오늘의 그린일기');
        const [inputCount, setInputCount] = useState(0);
        const navigate = useNavigate();

    // const Modal = () => {
    //     const [message, setMessage] = useState('');
    // }

    const proverbs = [
        "일회용품", "재활용", "물", "전기", "배달", "걷기", "카페-텀블러", "분리수거", "제로웨이스트쇼핑", "친환경", 
        "멸종위기", "에코백", "설거지", "자전거", "대중교통", "미세먼지", "식물키우기", "손빨래", 
        "플로깅", "봉사활동", "포장", "헌옷수거"
        ];

    let onChange = (event) => {
            const value = event.target.value;
            setText(value);
            setLength(value.length);
          };

    // let onChange = (event) => { 
    //     setText(event.target.value); 
    //     setText(value);
    //     setLength(value.length);
    // }; 

    const clearTextarea = () => {
        setText(''); //글을 지워주는 기능
    }

    const getRandomIndex = (length) => {
        return Math.floor(Math.random() * length)
    };

    const MAX_LENGTH = 200;

    useEffect(() => {
        if (isOpen) {
        setPlaceholder(proverbs[getRandomIndex(proverbs.length)]);
        setTitle(proverbs[getRandomIndex(proverbs.length)]);
        }
    }, [isOpen]);

    const handleSubmit = async (event) => { //입력 필드에서 입력이 변경될 때마다 호출되며, 입력된 값을 setText를 통해 상태 text에 저장
        alert("저장됐습니다.");
        const response = await fetch('https://codingapple1.github.io/shop/data2.json', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ text }),//content 상태 값을 json으로 변환하여 요청 본문에 포함시킴
        });
        // if (text.trim() === '') { //입력필드가 비어있는지 검사, 공백이나 빈칸 제출 못하게함
        //     setText('');
        //     setMessage('입력된 내용이 없습니다.');
        //     return;
        // }
        
        onSubmit(text); // 글 등록 버튼 클릭 시 동작
        setText(''); // 텍스트 입력칸을 초기화=> 다음 입력위해 빈필드갖게함
        setMessage('저장됐습니다.');
        onClose(); //저장하면 하로 닫기게함 지체ㄴㄴ
    };

    
    if (!isOpen) {
        return null; // 모달이 닫혀 있을 때는 렌더링하지 않음
    }
    
    return (
            <div className="modal">
                    <button className="Close-button" onClick={() => {onClose(); clearTextarea();}}>
                        close
                    </button>
                    <h3>🌞 오늘의 주제  {'<'}{title}{'>'} 🌞</h3>
                    <div style={{ display: isOpen ? 'block' : 'none' }}> //isOpen이'block'이면 요소가 보이고, 'none'이면 요소가 보이지 않음
                    <form onSubmit={handleSubmit}>
                        <textarea 
                            className ="diarytextarea"
                            maxLength={199}
                            // maxLength="200" alert="200자가 다 채워졌습니다."
                            value={text} //입력된 값을 text와 연결
                            onChange={onChange} // 입력된 텍스트 변경 시 상태 업데이트
                            placeholder="오늘의 일기를 작성하세요. 일기 작성 후 사과를 눌러 일기를 저장해주세요!"
                            style={{ whiteSpace: "pre-wrap" }}
                        ></textarea>
                        <div className="diaryinputLength">{length}/200자</div>
                        <button className="Save-button" onClick={handleSubmit}>
                            <img src='apple.png' alt="Save" />
                        </button>
                        </form>
                        </div>
                        {message && <p>{message}</p>}
             </div>
    );
};

export default Modal;
