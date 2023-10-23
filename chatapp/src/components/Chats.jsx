import React, { useState } from "react";

//prendre la liste des

const Chats = () => {
    const [chats, setChats] = useState([]);

    return(
        <div className="chats">
            {Object.entries(chats)?.sort((a,b)=>b[1].date - a[1].date).map((chat) => (
                <div className="userChat" key={chat[0]} onClick="">
                    <img src="" alt="" /> {/* mettre url */}
                    <div className="userChatInfo">
                        <span>Ami1</span> {/* mettre pseudo */}
                        <p>Hello</p> {/* mettre dernier message */}
                    </div>
                </div>
            ))}
        </div>
    )
}

export default Chats;