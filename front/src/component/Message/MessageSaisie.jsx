import React, { useState } from 'react';

function MessageSaisie({ onMessageSubmit }) {
  const [message, setMessage] = useState('');

  const handleSubmit = () => {
    if (message.trim() !== '') {
      onMessageSubmit(message);
      setMessage('');
    }
  };

  return (
    <div>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Écrivez votre message..."
      />
      <button onClick={handleSubmit}>Envoyer</button>
    </div>
  );
}

export default MessageSaisie;
