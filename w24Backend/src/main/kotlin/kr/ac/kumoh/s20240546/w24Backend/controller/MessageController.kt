package kr.ac.kumoh.s20240546.w24Backend.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["https://wsp20240546frontend.netlify.app"])
@RequestMapping("/api/message")
class MessageController {
    @GetMapping
    fun getRoot() = mapOf("status" to "success")
    @GetMapping("/api/message")
    fun getMessage() = mapOf("status" to "success")
}