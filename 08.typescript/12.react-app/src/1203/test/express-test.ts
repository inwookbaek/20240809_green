import express from 'express'
const app = express(), port = 4000

app
  .get('/', (req, res) => res.json({message: "Hello Express??"}))
  .listen(port, () => console.log(`http://localhost:${port} started...`))