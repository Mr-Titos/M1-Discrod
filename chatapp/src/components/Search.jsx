import React, { useState } from "react";

const Search = () => {
  const [username, setUsername] = useState("");
  const [user, setUser] = useState(null);
  const [err, setErr] = useState(false);

  const handleSearch = () => {
    try {
      //setUser
    } catch (err) {
      setErr(true);
    }
  }

  const handleKey = (e) => {
    e.code === "Enter" && handleSearch();
  };

  const handleSelect = () => {
    //checker si conversation existe, sinon en créer une nouvelle
    setUser(null);
    setUsername("");
  }

  return(
    <div className="search">
      <div className="searchForm">
        <input
        type="text"
        placeholder="Trouver un utilisateur"
        onKeyDown={handleKey}
        onChange={(e) => setUsername(e.target.value)}
        value={username}
          />
      </div>
      {err && <span>Utilisateur non trouvé !</span>}
      {user && (
        <div className="userChat" onClick={handleSelect}>
          <img src="" alt="" /> {/* mettre url */}
          <div className="userChatInfo">
            <span>NomTrouvé</span> {/* mettre pseudo */}
          </div>
        </div>
      )}
    </div>
  )
}

export default Search;