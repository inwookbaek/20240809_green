import React, { useState } from "react";

export default function AddTodo({ onAddTodo }) {
  const [title, setTitle] = useState("");

  return (
    <div>
      <input
        type='text'
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <button
        onClick={() => {
          setTitle("");
          onAddTodo(title);
        }}
      >
        Add
      </button>
    </div>
  );
}
