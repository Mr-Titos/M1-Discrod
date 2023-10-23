import React, { useState } from "react";
import Img from "../img/img.png";
import Attach from "../img/attach.png";

const Input = () => {
    const [text, setText] = useState("");
    const [img, setImg] = useState(null);

    const handleSend = async () => {
        if (img) {
            //envoier une image
        }else{
            //envoyer un texte
        }

        setText("");
        setImg(null);
    }

    return(
        <div className="input">
            <input type="text" placeholder="Envoyer un message ..." onChange={(e) => setText(e.target.value)} value={text}/>
            <div className="send">
                <img src={Attach} alt="" />
                <input type="file" style={{display:"none"}} id="file" onChange={(e) => setImg(e.target.files[0])}/>
                <label htmlFor="file">
                    <img src={Img} alt="" />
                </label>
                <button onClick={handleSend}>Envoyer</button>
            </div>
        </div>
    )
}

export default Input;