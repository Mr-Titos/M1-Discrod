import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';
import MessageSaisie from './component/Message/MessageSaisie';
import MessageListe from './component/Message/MessageListe';
import Sidebar from './component/Sidebar/Sidebar'; 

function App() {

  const [messages, setMessages] = useState([]);

  const handleMessageSubmit = (message) => {
    setMessages([...messages, message]);
  };

  return (
    <div className="app">
      {/*sidebar*/}
      <div className="content">
        <h1>Messages</h1>
        <MessageSaisie onMessageSubmit={handleMessageSubmit} />
        <MessageListe messages={messages} />
      </div>
    </div>
  );
}

export default App;
