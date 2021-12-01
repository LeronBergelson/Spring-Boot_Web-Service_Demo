package com.example.SpringDatabase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SpringBootApplication
class SpringDatabaseApplication

fun main(args: Array<String>) {
	runApplication<SpringDatabaseApplication>(*args)
}
interface PlayerDataRepository: JpaRepository<playerdata, Long>{

}

@RestController
@RequestMapping("api")
class PlayerDataRestController(val PlayerDataRepo: PlayerDataRepository){
	@GetMapping("PlayerData")
	fun GetAllPlayerEntries() = PlayerDataRepo.findAll()
}

@Entity
class playerdata(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val Id: Long = 0, var XCoord: Float = 0.0f, var YCoord: Float = 0.0f, var ZCoord: Float = 0.0f
)