package kr.ac.kumoh.k20230546.demo.Repository

import kr.ac.kumoh.k20230546.demo.Model.LottoNumber

interface LottoRepository {
    fun save(lottoNumber: LottoNumber) : LottoNumber
    fun find() : LottoNumber?
}