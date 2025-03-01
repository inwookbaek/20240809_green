import React from "react";

const MyEvent = () => {
  const [message, setMessage] = React.useState("");

  return (
    <div>
      <input
        type='text'
        placeholder='onChange...'
        name='msg'
        value={message}
        onChange={(e) => {
          console.log(e.target.value);
          setMessage(e.target.value);
        }}
      />
    </div>
  );
};

export default MyEvent;
