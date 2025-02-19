import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "@emotion/styled";

const Container = styled.div`
  max-width: 800px;
  margin: auto;
  padding: 20px;
`;

const ProductList = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8090/api/products").then((response) => {
      setProducts(response.data);
    });
  }, []);

  return (
    <Container>
      <h2>상품 목록</h2>
      <ul>
        {products.map((product) => (
          <li key={product.id}>
            {product.name} - {product.price}원
          </li>
        ))}
      </ul>
    </Container>
  );
};

export default ProductList;
