import React, { Component } from "react";

class State01_Counter extends Component {
  // 1
  constructor(props) {
    // 2
    super(props); // 3
    this.person = {
      name: "aaa",
      tel: "1111",
    };

    this.state = {
      // 4
      count: 0, // 5
      name: "aaa",
    };
    console.log("==>  ", props);
  }

  render() {
    // 6
    return (
      <div>
        <h1>Name: {this.name}</h1>
        <h1>Count: {this.state.count}</h1>
        <button onClick={() => this.setState({ count: this.state.count + 1 })}>
          Increment
        </button>
        <button onClick={() => this.setState({ count: this.state.count - 1 })}>
          Decrement
        </button>
      </div>
    );
  }
}

State01_Counter.defaultProps = {
  name: "gilbaek",
};

export default State01_Counter;
