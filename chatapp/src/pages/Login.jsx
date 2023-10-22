import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
    const [err, setErr] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        //const email = e.target[0].value;
        //const password = e.target[1].value;
    
        try {
          //
          navigate("/")
        } catch (err) {
          setErr(true);
        }
    };

    return(
        <div className="formContainer">
            <div className="formWrapper">
                <span className="logo">Discrod</span>
                <span className="title">Connexion</span>
                <form onSubmit={handleSubmit}>
                    <input required type="email" placeholder="Email" />
                    <input required type="password" placeholder="Mot de passe" />
                    <button >Se connecter</button>
                    {err && <span>Une erreur est survenue</span>}
                </form>
                <p>Vous n'avez pas de compte ? <Link to="/register">S'inscrire</Link></p>
            </div>  
        </div>
    )
}

export default Login;