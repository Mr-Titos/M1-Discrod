import React from "react";
import { useNavigate } from "react-router-dom";


const Navbar = () => {
    const navigate = useNavigate();

    const deconnexion = () =>{
        navigate("/login");
    }

    return(
        <div className='navbar'>
            <span className="logo">Discrod</span>
            <div className="user">
                <img src="" alt="" /> {/* mettre url */}
                <span>JOJO</span> {/* mettre pseudo */}
                <button onClick={()=>deconnexion()}>Déconnexion</button>
            </div>
        </div>
    )
}

export default Navbar;