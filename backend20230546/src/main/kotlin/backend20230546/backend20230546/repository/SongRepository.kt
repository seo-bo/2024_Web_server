package backend20230546.backend20230546.repository

import backend20230546.backend20230546.model.Song
import org.springframework.data.mongodb.repository.MongoRepository

interface SongRepository : MongoRepository<Song,String>
{
    fun findBySinger(singer: String):List<Song>
}