import './App.css'
import { useState, useEffect } from 'react'
import axios from 'axios'
import Container from './Container.js'

const SERVER_URL = 'http://localhost:8080/api/songs'

const App = () => {
 const [songs, setSongs] = useState([])
 const getSong = async () =>{
    try {
      const res = await axios.get(SERVER_URL)
      console.log(res)
      setSongs(res.data)
    } catch (err){
      console.log(err)
      setSongs([])
    }
 }
 useEffect(() => {
  getSong()
 },[])

  return (
    <div>
      <Header/>
      <Playlist 
        title="프로그래밍하면서 듣고 싶은 노래"
        listSong={songs}/>
    </div>
  )
}

const Header = () => {
  return (
      <h1>React 프로그래밍</h1>
  )
}

const Playlist = ({title,listSong}) => {
  return (
    <div className='playlist'>
      <div className="playlist">{title}</div>
      {
        listSong.map(song => 
          <Container key={song.id} song={song}/>
        )
      }
    </div>
  )
}

export default App
