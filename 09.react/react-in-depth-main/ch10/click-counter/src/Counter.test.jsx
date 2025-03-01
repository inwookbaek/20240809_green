import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import "@testing-library/jest-dom";

import { Counter } from "./Counter";

function setup(start) {
  const { rerender } = render(<Counter start={start} />);
  const getCounter = (v) =>
    screen.getByRole("heading", { name: `Counter: ${v}` });
  const increment = screen.getByRole("button", { name: "Increment" });
  const decrement = screen.getByRole("button", { name: "Decrement" });
  const user = userEvent.setup();
  return {
    getCounter,
    increment,
    decrement,
    user,
    rerender: (newStart) => rerender(<Counter start={newStart} />),
  };
}

test("Counter should start at the given value", () => {
  // ARRANGE
  const { getCounter } = setup(10);
  // ASSERT
  expect(getCounter(10)).toBeInTheDocument();
});

test("Counter should start at 0 if no value is given", () => {
  // ARRANGE
  const { getCounter } = setup();
  // ASSERT
  expect(getCounter(0)).toBeInTheDocument();
});

test("Counter should increment when button is pressed", async () => {
  // ARRANGE
  const { getCounter, increment, user } = setup();
  // ACT
  await user.click(increment);
  // ASSERT
  expect(getCounter(1)).toBeInTheDocument();
});

test("Counter should decrement when button is pressed", async () => {
  // ARRANGE
  const { getCounter, decrement, user } = setup();
  // ACT
  await user.click(decrement);
  // ASSERT
  expect(getCounter(-1)).toBeInTheDocument();
});

test("Counter should not update value if passed start property changes", () => {
  // ARRANGE
  const { getCounter, rerender } = setup(10);
  // ACT
  rerender(20);
  // ASSERT
  expect(getCounter(10)).toBeInTheDocument();
});
