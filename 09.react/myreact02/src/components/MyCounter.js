import React from "react";

const MyCounter = () => {
  const [count, setCount] = React.useState(0);

  function handleAdd() {
    setCount(count + 1);
  }
  function handleSub() {
    setCount(count - 1);
  }

  return (
    <div>
      <input type='text' value={count} />
      {" = "}
      Count: {count}
      {" ... "}
      <button onClick={handleAdd}>Add +1</button>
      <button onClick={handleSub}>Sub -1</button>
      {" / "}
      <button
        onClick={() => {
          setCount(count + 1);
        }}
      >
        +1
      </button>
      <button
        onClick={() => {
          setCount(count - 1);
        }}
      >
        -1
      </button>
      <br />
    </div>
  );
};

export default MyCounter;
