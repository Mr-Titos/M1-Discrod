import React, { useEffect, useRef } from "react";

const Message = ({message}) => {
    const ref = useRef();

    useEffect(() => {
        ref.current?.scrollIntoView({ behavior: "smooth" });
      }, [message]);

    return(
        <div className="message owner">
            <div className="messageInfo">
                <img src="" alt="" />
                <span>A l'instant</span>
            </div>
            <div className="messageContent">
                <p>hello</p>
                <img src="" alt="" />
            </div>
        </div>
    )
}

export default Message;