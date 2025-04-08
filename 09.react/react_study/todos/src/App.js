import React, { useState } from "react";
import LoginToggle from "./LoginToggle";
import AddTodo from "./components/AddTodo";
import TodoList from "./components/TodoList";

const sampleTodos = [
  { id: 1, text: "Todo 1", done: false },
  { id: 2, text: "Todo 2", done: false },
  { id: 3, text: "Todo 3", done: false },
];

function App() {
  const [todos, setTodos] = useState(sampleTodos);

  function handleAddTodo(title) {
    setTodos([
      ...todos,
      {
        id: todos.length + 1,
        text: title,
        done: false,
      },
    ]);
  }

  function handleChangeTodo() {
    console.log("handleChangeTodo");
  }

  function handleDeleteTodo() {
    console.log("handleDeleteTodo");
  }

  return (
    <div className='App'>
      <LoginToggle />
      <AddTodo onAddTodo={handleAddTodo} />
      <TodoList
        todos={todos}
        onChangeTodo={handleChangeTodo}
        actionDeleteTodo={handleDeleteTodo}
      />
    </div>
  );
}

export default App;
