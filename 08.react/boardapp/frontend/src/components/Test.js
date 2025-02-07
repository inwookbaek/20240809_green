import React from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";

const Test = () => {
  const handleTest = async () => {
    try {
      const response = await axios.get("http://localhost:8090/members/test");
      console.log("==========> ", response.data);
    } catch (error) {
      console.error("Test failed:", error);
    }
  };

  return (
    <Button variant='primary' onClick={handleTest} className='mt-3'>
      Test
    </Button>
  );
};

export default Test;
