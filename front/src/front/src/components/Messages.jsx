import React, { useState, useEffect } from "react";
import Message from "../components/Message";
import API from "../RestClient"


const Messages = () => {
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        async function fetchMessages() {
            const id = localStorage.getItem("channelID");
            try {
                const response = await API.GetMessages("channel/"+id);
                setMessages(response); 
            } catch (error) {
                console.error(error);
            }
        }
        fetchMessages(); 
    }, []);

    return(
        <div className="messages">
            {messages && messages.length > 0 ? (
                messages.map((element) => {
                    <Message message={element.text}/>
                })
                ) : (
                    <p>Aucune message.</p>
                )}
        </div>
    )
}

export default Messages;