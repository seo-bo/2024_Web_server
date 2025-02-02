package k20230546.i.mweek.controller

import ch.qos.logback.core.model.Model
import k20230546.i.mweek.service.CardService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CardController(private val service: CardService) {
    @GetMapping("/cards/random")
    fun getRandomCards(model: org.springframework.ui.Model): String {
        service.dealCards()

        val cards = service.getAllCards()

        //model.addAttribute("cards", cards[0].imageName)
        model.addAttribute("cards", cards.map { it.imageName })
        return "cards"
    }
}