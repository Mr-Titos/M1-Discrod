import React from "react";
import { useNavigate } from "react-router-dom";
import Img from "../img/img.jpg"

const Navbar = () => {
    const navigate = useNavigate();

    const deconnexion = () =>{
        navigate("/login");
    }

    return(
        <div className='navbar'>
            <span className="logo">Discrod</span>
            <div className="user">
                <img src={Img} alt="" /> 
                <span>{localStorage.getItem("username")}</span>
                <button onClick={()=>deconnexion()}>Déconnexion</button>
            </div>
        </div>
    )
}

export default Navbar;