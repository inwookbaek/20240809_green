const MyComponent = (props) => {
  return (
    <div>
      {props.name} / {props.age} / {props.addr} / {props.children}
      <br />
    </div>
  );
};

export default MyComponent;
