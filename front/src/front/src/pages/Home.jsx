import React from "react";
import Sidebar from "../components/Sidebar";
import Chat from "../components/Chat";
import { useLocation } from "react-router-dom";


const Home = () => {

    const token = localStorage.getItem("token");
    
    console.log(token);

    return(
        <div className="home">
            <div className="container">
                <Sidebar/>
                <Chat/>
            </div>
        </div>
    )
}

export default Home;