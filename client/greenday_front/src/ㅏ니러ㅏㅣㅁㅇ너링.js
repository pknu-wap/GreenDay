function modalToken({ setGetToken, setUserInfo }) {

    const userAccessToken = () => {
        const url = new URL(window.location.href);
        const token = url.searchParams.get("access_token");
        if (token) {
        getToken(token);
        }
    };
    
    const getToken = (token) => {
        console.log(token);
        localStorage.setItem("access_token", token);
        setGetToken(token);
    };
};


async function greenDiary() {
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
        window.location.href = "/modiary";
    } catch (error) {
        console.error("오류:", error);
    }
}

const handleSubmit = async (event) => {
    event.preventDefault(); // 기본 폼 제출 동작 방지

    try {
        const jwtToken = localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).jwtToken : null;
        if (!jwtToken) {
            // JWT 토큰이 없으면 처리
            console.error("JWT 토큰이 없습니다.");
            return;
        }

        const response = await fetch('http://localhost:8080/post/write_diary', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify({ text }), // content 상태 값을 JSON으로 변환하여 요청 본문에 포함
        });

        if (!response.ok) {
            // 요청이 성공하지 않았을 때 처리
            console.error("요청 실패:", response.statusText);
            return;
        }
    
        // 요청 성공 시 처리
        alert("저장됐습니다.");
        console.log("응답 데이터:", data);
    } catch (error) {
        console.error("API 요청 오류:", error);