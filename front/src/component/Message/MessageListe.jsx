// MessageList.js
import React from 'react';

function MessageListe({ messages }) {
  return (
    <ul>
      {messages.map((message, index) => (
        <li key={index}>{message}</li>
      ))}
    </ul>
  );
}

export default MessageListe;
