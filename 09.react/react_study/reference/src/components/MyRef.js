import React from "react";
import "./MyRef.css";

function MyRef() {
  const [state, setState] = React.useState({
    password: "",
    clicked: false,
    validated: false,
  });

  function handleChange(e) {
    setState({
      password: e.target.value,
    });
  }

  function handleButtonClick() {
    setState({
      clicked: true,
      validated: state.password === "0000",
    });
  }

  return (
    <div>
      <input
        type='password'
        value={state.password}
        onChange={handleChange}
        className={
          state.clicked ? (state.validated ? "success" : "failure") : ""
        }
      />
      <button onClick={handleButtonClick}>Validate</button>
    </div>
  );
}

export default MyRef;
