package kr.ac.kumoh.s20230546.w24w05thymeleaf.Service

import kr.ac.kumoh.s20230546.w24w05thymeleaf.repository.SongRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class SongSer (val repository: SongRepository)
{
    fun getRandom() = repository.getSong(Random.nextInt(repository.songsSize))
    fun getsong() = repository.fetchSong()
}