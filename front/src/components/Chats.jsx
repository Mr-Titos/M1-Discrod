import React, { useState, useEffect } from "react";
import Vocal from "../img/vocal.jpg"
import Text from "../img/text.jpg"
import API from "../RestClient"


//prendre la liste des discussions
const Chats = () => {
    const [chats, setChats] = useState([]);

    useEffect(() => {
        async function fetchChats() {
            try {
                const response = await API.Get("channel");
                setChats(response); 
            } catch (error) {
                console.error(error);
            }
        }

        fetchChats(); 

    }, []);

    return(
        <div className="chats">
        {chats && chats.length > 0 ? (
            chats.map((element) => {
                let channelTypeImage;
                
                if (element.channelType=="VOCAL") {
                    channelTypeImage = Vocal; 
                }else{
                    channelTypeImage = Text; 
                }
    
                return (
                    <div className="userChat" key={element.id} onClick="">
                        <img src={channelTypeImage} alt="" />
                        <div className="userChatInfo">
                            <span>{element.name}</span>
                            <p>{element.channelType}</p>
                        </div>
                    </div>
                );
            })
        ) : (
            <p>Aucune discussion disponible.</p>
        )}
    </div>
    
    )
}

export default Chats;