package kr.ac.kumoh.k20230546.demo.Repository

import kr.ac.kumoh.k20230546.demo.Model.LottoNumber
import org.springframework.stereotype.Repository

@Repository
class InMemoryLottoRepository : LottoRepository
{
    private var storedLottoNumber: LottoNumber? = null

    override fun save(lottoNumber: LottoNumber): LottoNumber {
        storedLottoNumber = lottoNumber
        return storedLottoNumber!!
    }

    override fun find(): LottoNumber? {
        return storedLottoNumber
    }
}