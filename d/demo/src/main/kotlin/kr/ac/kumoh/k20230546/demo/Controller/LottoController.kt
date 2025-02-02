package kr.ac.kumoh.k20230546.demo.Controller

import kr.ac.kumoh.k20230546.demo.Service.LottoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LottoController(val service: LottoService) {
    @GetMapping("/lotto/random")
    fun generateNumbers(model : Model) : String
    {
        val lottoNumber = service.getLuckyNumber()
        model.addAttribute("numbers", lottoNumber.numbers)
        return "lotto"
    }
}