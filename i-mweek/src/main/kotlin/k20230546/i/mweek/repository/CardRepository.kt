package k20230546.i.mweek.repository

import k20230546.i.mweek.model.Card

interface CardRepository {
    val size: Int
    fun add(card: Card)
    fun getAllCards(): List<Card>
    fun clear()
}