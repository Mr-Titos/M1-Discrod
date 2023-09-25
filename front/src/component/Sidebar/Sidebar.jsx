import React from 'react';
import './Sidebar.css'; // Importez le fichier de style pour la sidebar

function Sidebar() {
  return (
    <div className="sidebar">
      {/* Sidebar gauche (20%) */}
      <div className="sidebar-left">
        {/* Contenu de la première barre latérale */}
        <ul>

          {/* Ajoutez ici vos icônes */}
        </ul>
      </div>

      {/* Sidebar droite (70%) */}
      <div className="sidebar-right">
        {/* Contenu de la deuxième barre latérale */}
        
      </div>
    </div>
  );
}

export default Sidebar;
