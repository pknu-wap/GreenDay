import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <body>
        <h1>
          방문자님,<br />

          환영합니다.<br /><br />
          <div class="one"></div>
        </h1>
        <ul>
          <li><a href="app.js">홈</a></li><br />
          <li><a href="notice.js">게시판</a></li><br />
          <li><a href="history.js">히스토리</a></li><br />
          <div class = "live_list"><li><a href="greenmate.js">그린메이트</a></li></div><br />
        </ul>
      </body>
    </div>
  );
}

export default App;
