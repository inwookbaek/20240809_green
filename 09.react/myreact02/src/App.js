import "./App.css";
import MyComponent from "./components/MyComponent";
import MyCounter from "./components/MyCounter";
import MyEvent from "./components/MyEvent";
import MyRef from "./components/MyRef";
import MyRef2 from "./components/MyRef2";
import MyRef3 from "./components/MyRef3";

function App() {
  return (
    <div>
      <MyComponent name='홍길동' age='1000' addr='한양'>
        자식
      </MyComponent>
      <MyCounter />
      <MyEvent />
      <MyRef />
      <MyRef2 />
      <MyRef3 />
    </div>
  );
}

export default App;
