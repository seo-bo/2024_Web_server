package kr.ac.kumoh.s20230546.w24w05thymeleaf.controller

import kr.ac.kumoh.s20230546.w24w05thymeleaf.Service.SongSer
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SongController(val service : SongSer)
{
    @GetMapping("/song/random")
    fun getRandomSong(model: Model) : String
    {
        model.addAttribute("song", service.getRandom())
        return "random"
    }
}