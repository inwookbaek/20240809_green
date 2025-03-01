import { useState, useEffect } from 'react';

const Card = ({title}) => {
  const [count, setCount] = useState(0)
  const [isLike, setLike] = useState(false);
  useEffect(()=> {
    console.log(`${title}영화 좋아요? ${isLike ? '좋아요' : '몰라요'}}`)
  }, [isLike])

  const onClickLikeButtonHandler = () => {
    setLike(!isLike);
  }
  return (
    <div className='card'>
      <h2 onClick={()=> setCount(count+1)}>{title}</h2>
      <b>{count || null}</b>
      <button onClick={onClickLikeButtonHandler}>
        {isLike ? '💗' : '🤍'}
      </button>
    </div>
  );
}

function App() {
  return (
    <div className="card-container">
      <Card title="Star Wars"/>
      <Card title="Cats"/>
      <Card title="Avatar"/>
    </div>
  );
}

export default App;
