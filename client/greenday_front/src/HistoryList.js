import React, {useEffect, useState} from "react"
import axios from "axios"


const HistoryList = () => {
    const [HistoryList, setHistoryList] = useState([]);

    const getBoardList = async () => {
      const resp = (await axios.get('//localhost:8080/history')).data
      setHistoryList(resp.date)

      const pngn = resp.pagination;
      console.log(HistoryList);
    }

    useEffect(() => {
        getHistoryList();
    }, []);

    return (
        <div>
            <ul>
                {HistoryList.map((board) => (
                    <li key={board.idx}><board.title></board.title></li>
                ))}
            </ul>
        </div>
    );
};

export default HistoryList;