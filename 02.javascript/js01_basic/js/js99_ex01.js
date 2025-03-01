const button = document.querySelector('#toggle-button');
const text = document.querySelector('#hidden-text');

button.addEventListener('click', function() {
  if(text.classList.contains("hidden")) {
      text.classList.remove('hidden');
      text.classList.add('visible');
      button.textContent = "감추기";
  } else {
      text.classList.remove('visible');
      text.classList.add('hidden');
      button.textContent = "보여주기";
  }
});

