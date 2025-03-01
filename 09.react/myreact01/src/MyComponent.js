const MyComponent = ({ name = "aaa", tel = "1111", children }) => {
  // const { name, tel, children } = props;
  return (
    <p>
      안녕하세요? 제 이름은 {name} / {tel} 입니다!!!!
      <br />
      children 값 = {children}
    </p>
  );
};

export default MyComponent;
