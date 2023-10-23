import React, { useState } from "react";
import Add from "../img/addAvatar.png";
import { useNavigate, Link } from "react-router-dom";

const Register = () => {
    const [err, setErr] = useState(false);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) =>{
        setLoading(true);
        e.preventDefault();
        //const pseudo = e.target[0].value;
        //const email = e.target[1].value;
        //const password = e.target[2].value;
        //const file = e.target[3].files[0];

        try {
            //
            navigate("/");
        } catch (err) {
            setErr(true);
            setLoading(false);
        }
    };

    return(
        <div className="formContainer">
            <div className="formWrapper">
                <span className="logo">Discrod</span>
                <span className="title">Inscription</span>
                <form onSubmit={handleSubmit}>
                    <input required type="text" placeholder="Pseudonyme" />
                    <input required type="email" placeholder="Email" />
                    <input required type="password" placeholder="Mot de passe" />
                    <input required style={{ display: "none" }} type="file" id="file" />
                    <label htmlFor="file">
                        <img src={Add} alt="" />
                        <span>Add an avatar</span>
                    </label>
                    <button >S'inscrire</button>
                    {loading && "Uploading and compressing the image please wait..."}
                    {err && <span>Une erreur est survenue</span>}
                </form>
                <p>Vous avez déjà un compte ? <Link to = "/login" >Se connecter</Link></p>
            </div>  
        </div>
    )
}

export default Register;