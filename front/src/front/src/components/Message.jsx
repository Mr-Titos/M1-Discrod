import React, { useEffect, useRef } from "react";
import Img from "../img/img.jpg"


const Message = ({message}) => {
    const ref = useRef();

    useEffect(() => {
        ref.current?.scrollIntoView({ behavior: "smooth" });
      }, [message]);

    return(
        <div className="message owner"> 
            <div className="messageInfo">
                <img src={Img} alt="" />
                <span>A l'instant</span>
            </div>
            <div className="messageContent">
                <p>{message}</p>
                <img src="" alt="" /> 
            </div>
        </div>
    )
}

export default Message;