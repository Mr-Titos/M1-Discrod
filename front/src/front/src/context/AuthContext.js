import { createContext, useContext, useState, useEffect } from "react";

// Créez un context personnalisé pour l'authentification
export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Simulez l'authentification ici (vous pouvez remplacer cela par votre propre logique d'authentification)
  useEffect(() => {
    // Par exemple, chargez l'utilisateur depuis le stockage local (localStorage) s'il existe
    const savedUser = localStorage.getItem("user");
    if (savedUser) {
      setCurrentUser(JSON.parse(savedUser));
    }

    setLoading(false);
  }, []);

  const login = (user) => {
    // Enregistrez l'utilisateur dans l'état et le stockage local (localStorage)
    setCurrentUser(user);
    localStorage.setItem("user", JSON.stringify(user));
  };

  const logout = () => {
    // Déconnectez l'utilisateur de l'état et supprimez-le du stockage local
    setCurrentUser(null);
    localStorage.removeItem("user");
  };

  return (
    <AuthContext.Provider value={{ currentUser, login, logout }}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthContextProvider");
  }
  return context;
};
