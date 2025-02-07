import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const PostForm = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [author, setAuthor] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/posts", {
        title,
        content,
        author,
      });
      navigate(`/posts/${response.data.id}`);
    } catch (error) {
      console.error("Error creating post:", error);
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId='title'>
        <Form.Label>제목</Form.Label>
        <Form.Control
          type='text'
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
      </Form.Group>
      <Form.Group controlId='content'>
        <Form.Label>내용</Form.Label>
        <Form.Control
          as='textarea'
          rows={3}
          value={content}
          onChange={(e) => setContent(e.target.value)}
          required
        />
      </Form.Group>
      <Form.Group controlId='author'>
        <Form.Label>작성자</Form.Label>
        <Form.Control
          type='text'
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
          required
        />
      </Form.Group>
      <Button variant='primary' type='submit'>
        작성
      </Button>
    </Form>
  );
};

export default PostForm;
