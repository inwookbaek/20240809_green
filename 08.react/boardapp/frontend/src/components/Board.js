import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Pagination from "react-bootstrap/Pagination";
import Table from "react-bootstrap/Table";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";

const Board = () => {
  const [posts, setPosts] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  // 📌 게시글 목록 가져오기
  const fetchPosts = useCallback(async () => {
    setLoading(true);
    try {
      const response = await axios.get("http://localhost:8090/board/list", {
        params: { keyword, page, size: 10 },
      });

      setPosts(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error("Error fetching posts:", error);
    } finally {
      setLoading(false);
    }
  }, [keyword, page]);

  // 📌 컴포넌트가 처음 렌더링될 때 & page, keyword 변경 시 실행
  useEffect(() => {
    fetchPosts();
  }, [fetchPosts]);

  // 🔹 검색 기능
  const handleSearch = (e) => {
    e.preventDefault();
    setPage(0);
    fetchPosts();
  };

  // 🔹 검색 Enter 키 이벤트
  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      handleSearch(e);
    }
  };

  // 🔹 페이지 이동
  const handlePageChange = (newPage) => {
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };

  return (
    <div>
      <h1 className='mb-4'>📌 게시판</h1>

      <Form onSubmit={handleSearch} className='mb-4 d-flex gap-2'>
        <Form.Control
          type='text'
          placeholder='검색어를 입력하세요...'
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          onKeyPress={handleKeyPress}
        />
        <Button variant='primary' type='submit'>
          검색
        </Button>
      </Form>

      {loading ? (
        <div className='text-center'>
          <Spinner animation='border' role='status' />
          <p>Loading...</p>
        </div>
      ) : (
        <>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>ID</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody>
              {posts.length > 0 ? (
                posts.map((post) => (
                  <tr key={post.id}>
                    <td>{post.id}</td>
                    <td>
                      <Link to={`/posts/${post.id}`}>{post.title}</Link>
                    </td>
                    <td>{post.author}</td>
                    <td>{new Date(post.createdAt).toLocaleString()}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan='4' className='text-center'>
                    게시글이 없습니다.
                  </td>
                </tr>
              )}
            </tbody>
          </Table>

          {totalPages > 1 && (
            <Pagination className='justify-content-center'>
              <Pagination.First
                onClick={() => handlePageChange(0)}
                disabled={page === 0}
              />
              <Pagination.Prev
                onClick={() => handlePageChange(page - 1)}
                disabled={page === 0}
              />
              {Array.from({ length: totalPages }, (_, i) => (
                <Pagination.Item
                  key={i}
                  active={i === page}
                  onClick={() => handlePageChange(i)}
                >
                  {i + 1}
                </Pagination.Item>
              ))}
              <Pagination.Next
                onClick={() => handlePageChange(page + 1)}
                disabled={page === totalPages - 1}
              />
              <Pagination.Last
                onClick={() => handlePageChange(totalPages - 1)}
                disabled={page === totalPages - 1}
              />
            </Pagination>
          )}
        </>
      )}
    </div>
  );
};

export default Board;
