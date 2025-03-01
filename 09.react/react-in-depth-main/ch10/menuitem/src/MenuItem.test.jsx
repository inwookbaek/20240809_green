import { render, screen, getByRole } from "@testing-library/react";
import "@testing-library/jest-dom";

import { MenuItem } from "./MenuItem";

test("MenuItem renders a link in a list item", () => {
  // ARRANGE
  render(<MenuItem href="/blog" label="Blog" />);

  // ASSERT
  const listItem = screen.getByRole("listitem");
  const link = getByRole(listItem, "link");
  expect(link).toHaveAttribute("href", "/blog");
  expect(link).toHaveAttribute("title", "Blog");
  expect(link).toHaveTextContent("Blog");
});
