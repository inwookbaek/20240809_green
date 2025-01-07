// DOMContentLoaded vs load의 차이 확인할 것
const todoInput = document. querySelector('#todo-input');
const addBtn = document.querySelector('#add-btn');
const todoList = document.querySelector('#todo-list');
const filterButtons = {
    all: document.querySelector('#all-tasks'),
    completed: document.querySelector('#completed-tasks'),
    incomplete: document.querySelector('#incomplete-tasks')
};

// Load saved todos
loadTodos();

addBtn.addEventListener('click', addTodo);
todoInput.addEventListener('keypress', e => {
  if(e.key === 'Enter') {
    addTodo();
  }
})

// 새로운 일정추가
function addTodo() {
  const todoText = todoInput.value.trim();
  if(todoText === '') return;
  // console.log(todoText);

  const todoItem = createTodoItem(todoText);
  todoList.appendChild(todoItem);

  savedTodos()
  todoInput.value = '';
}

// 새로운 일정추가(체크박스, 일정, 삭제버튼)
function createTodoItem(text, isCompleted=false) {
  const li = document.createElement('li');
  const checkbox = document.createElement('input');
  checkbox.type = 'checkbox';
  checkbox.checked = isCompleted;

  const span = document.createElement('span');
  span.textContent = text; 
  if(isCompleted) span.classList.add('completed');

  const deleteBtn = document.createElement('button');
  deleteBtn.textContent = '일정삭제';
  deleteBtn.classList.add('delete-btn');

  li.appendChild(checkbox);
  li.appendChild(span);
  li.appendChild(deleteBtn);
  
  return li;
}

// 일정목록저장
function savedTodos() {
  const todos = [];
  for(let todo of todoList.children) {
    const text = todo.querySelector('span').textContent;
    const isCompleted = todo.querySelector('input').checked;
    todos.push({text, isCompleted});
  }
  // localStorage사용법 확인할 것
  localStorage.setItem('todos', JSON.stringify(todos));
  console.log(todos);
}

filterButtons.all.addEventListener('click', showAllTodos);
filterButtons.completed.addEventListener('click', showCompletedTodos);
filterButtons.incomplete.addEventListener('click', showAllTodos);

// 전체일정 
function showAllTodos() {
  setVisibilityForTodos(() => true)
}

// 완료일정 
function showCompletedTodos() {
  setVisibilityForTodos(todo => todo.querySelector('input').checked)
}

// 미완료일정 
function showInCompleteTodos() {
  setVisibilityForTodos(todo => !todo.querySelector('input').checked)
}

// 전체, 완료, 미완료
function setVisibilityForTodos(condition) {
  const todos = todoList.children;
  for(let todo of todos) {
    todo.style.display = condition(todo) ? 'block' : 'none';
  }
}

// checkbox toggle, delete버튼이라면 삭제
todoList.addEventListener('click', e => {
  if(e.target.classList.contains('delete-btn')) {
    deleteTodo(e.target);
  } else if(e.target.tagName === 'INPUT' && e.target.type === 'checkbox') {
    toggleTodoCompelete(e.target);
  }
})

// 일정삭제
function deleteTodo(button) {
  button.parentElement.remove();
  savedTodos();
}

// 완료일정 toggle
function toggleTodoCompelete(checkbox) {
  const todoItem = checkbox.nextElementSibling;
  todoItem.classList.toggle('completed', checkbox.checked);
}

// localStorage의 일정을 로딩
function loadTodos() {
  const savedTodos = JSON.parse(localStorage.getItem('todos') || '[]');
  for(let todo of savedTodos) {
    const todoItem = createTodoItem(todo.text, todo.isCompleted);
    todoList.appendChild(todoItem);
  }

  console.log(savedTodos);
} 
