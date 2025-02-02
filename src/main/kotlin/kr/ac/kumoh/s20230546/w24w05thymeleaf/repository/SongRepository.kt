package kr.ac.kumoh.s20230546.w24w05thymeleaf.repository

import kr.ac.kumoh.s20230546.w24w05thymeleaf.model.Song
import org.springframework.stereotype.Repository

@Repository
class SongRepository
{
    protected  val songs = listOf(
        Song(1, "ㅇㅅㅇ","ㅡㅅㅡ"),
        Song(2, "ㅎㅅㅎ","uㅅu"),
        Song(3, "eㅅe","pㅅq"),
    )
    val songsSize: Int
        get() = songs.size
    fun getSong(idx : Int) = songs[idx]
    fun fetchSong() = songs
}