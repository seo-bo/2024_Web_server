package k20230546.i.mweek.repository

import k20230546.i.mweek.model.Card
import org.springframework.stereotype.Repository

@Repository
class InMemoryCardRepository : CardRepository {
    private val cards: MutableList<Card> = mutableListOf()

    override val size: Int
        get() = cards.size

    override fun add(card: Card) {
        if (!cards.contains(card)) {
            cards.add(card)
        }
    }

    override fun getAllCards(): List<Card> {
        return cards
    }

    override fun clear() {
        cards.clear()
    }
}