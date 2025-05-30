const form = document.querySelector('.chat-form');
const input = document.querySelector('.chat-input');
const messages = document.querySelector('.chat-messages');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  
  const userMessage = input.value;
  if (!userMessage) return;

  addMessage(userMessage, 'self');
  input.value = '';

  try {
    const res = await fetch('http://localhost:8080/answer', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ prompt: userMessage }),
    });

    const aiResponse = await res.text();
    addMessage(aiResponse, 'other');
  } catch (error) {
    console.error('Erro ao chamar backend:', error);
  }
});

function addMessage(text, type) {
  const div = document.createElement('div');
  div.classList.add(type === 'self' ? 'message--self' : 'message--other');
  div.textContent = text;
  messages.appendChild(div);
  messages.scrollTop = messages.scrollHeight;
}