package kr.ac.kumoh.k20230546.demo.Service

import kr.ac.kumoh.k20230546.demo.Model.LottoNumber
import kr.ac.kumoh.k20230546.demo.Repository.InMemoryLottoRepository
import kr.ac.kumoh.k20230546.demo.Repository.LottoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class LottoService(val lottoRepository : LottoRepository) {
    fun getLuckyNumber() : LottoNumber {
        var numbers = mutableSetOf<Int>()
        var n = 0
        while (numbers.size < 6) {
            numbers.add(Random.nextInt(1, 46))
        }
        val lottoNumber = LottoNumber(numbers.toSortedSet().toIntArray())
        return lottoRepository.save(lottoNumber)
    }
}