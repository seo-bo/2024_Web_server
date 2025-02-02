import React, {useEffect, useState} from 'react'
import './App.css';

const App = () =>
{
  const [message, setMessage] = useState('서버 접속 중...')
  const fetchData = async () =>
  {
    try
      {
        const apiUrl = process.env.REACT_APP_API_URL
        const response = await fetch(apiUrl)
        if(!response.ok)
        {
          throw new Error('Network response was not ok')
        }
        const data = await response.json()
        setMessage(data.status)
      }
    catch(error)
      {
        console.error('Error fetching data: ', error)
      }
  }
  useEffect(() =>
    {
        fetchData()
    }, [])
    
    return (
      <div className="App">
        <h1>으에에~~</h1>
        <p>{message}</p>
      </div>
    )
}

export default App;
