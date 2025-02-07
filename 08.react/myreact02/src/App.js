import "./App.css";
import React, { useState } from "react";
import AddTodo from "./AddTodo.js";
import TaskList from "./TaskList.js";

const btn2 = () => {
  alert("Button2 clicked");
};

const user = {
  name: "Hedy Lamarr",
  imageUrl: "https://i.imgur.com/yXOvdOSs.jpg",
  imageSize: 90,
};

function MyButton1() {
  const [isClicked, setIsClicked] = useState(false);
  const [count, setCount] = useState(0);
  const [text, setText] = useState("Hello, React!");

  const btn1 = () => {
    // alert("Button1 clicked");
    isClicked ? setIsClicked(false) : setIsClicked(true);
    console.log(isClicked);
  };

  function handleBtn1Click() {
    setCount(count + 1);
    setIsClicked(!isClicked);
    console.log("===> ", count, ", ---> ", isClicked);
  }

  return (
    <div>
      content : {text}
      {" Hello, React!   "}
      <input type='text' onChange={(e) => setText(e.target.value)} /> <br />
      <button onClick={btn1}>Click me! ({isClicked ? "true" : "false"})</button>
      <button onClick={handleBtn1Click}>Click me! ({count})</button>
    </div>
  );
}

function MyButton2() {
  return <button onClick={btn2}>Click me! (2)</button>;
}

const products = [
  { title: "Cabbage", isFruit: false, id: 1 },
  { title: "Garlic", isFruit: false, id: 2 },
  { title: "Apple", isFruit: true, id: 3 },
];

function ShoppingList() {
  const listItems = products.map((product) => (
    <li
      key={product.id}
      style={{
        color: product.isFruit ? "magenta" : "darkgreen",
      }}
    >
      {product.title}
    </li>
  ));

  return <ul>{listItems}</ul>;
}

function Form() {
  const [form, setForm] = useState({
    firstName: "Barbara",
    lastName: "Hepworth",
    email: "bhepworth@sculpture.com",
  });

  return (
    <>
      <label>
        First name:
        <input
          value={form.firstName}
          onChange={(e) => {
            setForm({
              ...form,
              firstName: e.target.value,
            });
          }}
        />
      </label>
      <label>
        Last name:
        <input
          value={form.lastName}
          onChange={(e) => {
            setForm({
              ...form,
              lastName: e.target.value,
            });
          }}
        />
      </label>
      <label>
        Email:
        <input
          value={form.email}
          onChange={(e) => {
            setForm({
              ...form,
              email: e.target.value,
            });
          }}
        />
      </label>
      <p>
        {form.firstName} / {form.lastName} / ({form.email})
      </p>
    </>
  );
}

let nextId = 3;
const initialTodos = [
  { id: 0, title: "Buy milk", done: true },
  { id: 1, title: "Eat tacos", done: false },
  { id: 2, title: "Brew tea", done: false },
];

export default function Myapp() {
  const [isCheck, setIsCheck] = useState(false);
  const [todos, setTodos] = useState(initialTodos);

  function handleCheck(e) {
    setIsCheck(e.target.checked);
  }

  function handleAddTodo(title) {
    setTodos([
      ...todos,
      {
        id: nextId++,
        title: title,
        done: false,
      },
    ]);
  }

  function handleChangeTodo(nextTodo) {
    setTodos(
      todos.map((t) => {
        if (t.id === nextTodo.id) {
          return nextTodo;
        } else {
          return t;
        }
      })
    );
  }

  function handleDeleteTodo(todoId) {
    setTodos(todos.filter((t) => t.id !== todoId));
  }

  return (
    <div align='center'>
      <h1>Hello, React!</h1>
      <MyButton1 />
      <MyButton2 />
      <hr />
      <h2>{user.name}</h2>
      <img
        className='avatar'
        src={user.imageUrl}
        alt={"Photo of " + user.name}
        style={{
          width: user.imageSize,
          height: user.imageSize,
        }}
      />
      <hr />
      <ShoppingList />
      <hr />
      <input type='checkbox' checked={isCheck} onChange={handleCheck} /> Check
      me! = Checked : {isCheck ? "checked" : "unChecked"}
      <hr />
      <Form />
      <hr />
      <AddTodo onAddTodo={handleAddTodo} />
      <TaskList
        todos={todos}
        onChangeTodo={handleChangeTodo}
        onDeleteTodo={handleDeleteTodo}
      />
    </div>
  );
}
