package com.example.SpringDatabase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
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

	@Query(value = "Call GetByPID(:PID)", nativeQuery = true)
	fun GetByPID(@Param("PID")PID: Int): playerdata
}

@RestController
@RequestMapping("api")
class PlayerDataRestController(val PlayerDataRepo: PlayerDataRepository)
{
	@GetMapping("PlayerData")
	fun GetAllPlayerEntries() = PlayerDataRepo.findAll()

	@GetMapping("PlayerData/{PID}")
	fun GetPID(@PathVariable(value = "PID") PID: Int) : playerdata
	{
		return PlayerDataRepo.GetByPID(PID)
	}

	@PostMapping("PlayerData")
	fun SavePlayerData(@RequestBody PlayerData: playerdata){
		PlayerDataRepo.save(PlayerData)
	}
}

@Entity
class playerdata(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val Id: Long = 0, var isvalid: Boolean = false, var pid: Int = -1, var health: Float = 100.0f, var XCoord: Float = 0.0f, var YCoord: Float = 0.0f, var ZCoord: Float = 0.0f
)