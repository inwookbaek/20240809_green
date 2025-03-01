import React, { useState } from "react";

export default function TodoList({ todos, onChangeTodo, onDeleteTodo }) {
  // console.log("TodoList -> todos", todos);

  return (
    <div>
      <ul>
        {todos.map((todo) => {
          return (
            <li key={todo.id}>
              <Todo
                todo={todo}
                onChange={onChangeTodo}
                onDelete={onChangeTodo}
              />
            </li>
          );
        })}
      </ul>
    </div>
  );
}

function Todo({ todo, onChange, onDelete }) {
  return (
    <label>
      <input
        type='checkbox'
        checked={todo.done}
        onChange={(e) => {
          onChange({
            ...todo,
            done: e.target.checked,
          });
        }}
      />
      {todo.text}
    </label>
  );
}
