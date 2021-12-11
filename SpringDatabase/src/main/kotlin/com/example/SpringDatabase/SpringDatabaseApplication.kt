package com.example.SpringDatabase

import org.apache.catalina.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//@SpringBootApplication
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SpringDatabaseApplication

fun main(args: Array<String>) {
	runApplication<SpringDatabaseApplication>(*args)
}
interface PlayerDataRepository: JpaRepository<playerdata, Long>{

	@Query(value = "Call GetByPID(:PID)", nativeQuery = true)
	fun GetByPID(@Param("PID")PID: Int): playerdata

	@Query(value = "Call UpdatePlayerData(:Email, :UserPassword, :IsValid, :PID, :Health, :XCoord, :YCoord, :ZCoord)", nativeQuery = true)
	fun UpdateData(@Param("Email")Email: String, @Param("UserPassword")UserPassword: String, @Param("IsValid")IsValid: Boolean, @Param("PID")PID: Int, @Param("Health")Health: Float,
				   @Param("XCoord")XCoord: Float, @Param("YCoord")YCoord: Float, @Param("ZCoord")ZCoord: Float) : playerdata

	@Query(value = "Call FindByEmail(:Email)", nativeQuery = true)
	fun findByEmail(@Param("Email")Email: String): playerdata?
}

@RestController
@RequestMapping("api/PlayerData")
class PlayerDataRestController(val PlayerDataRepo: PlayerDataRepository)
{
	@GetMapping()
	fun GetAllPlayerEntries() = PlayerDataRepo.findAll()

	@GetMapping("/{PID}")
	fun GetPID(@PathVariable(value = "PID") PID: Int) : playerdata
	{
		var PData = PlayerDataRepo.GetByPID(PID)
		return PData
	}

	@PostMapping("register")
	fun SavePlayerData(@RequestBody PlayerData: playerdata){
		PlayerDataRepo.save(PlayerData)
	}

	@PostMapping("login")
	fun login(@RequestBody PlayerData: playerdata): ResponseEntity<Any> {
		val player = this.PlayerDataRepo.findByEmail(PlayerData.email)
			?: return ResponseEntity.badRequest().body("User not found!")

		if(!player.comparePassword(PlayerData.userpassword)){
			return ResponseEntity.badRequest().body("Invalid Password!")
		}

		return ResponseEntity.ok(player)
	}


	@PutMapping("updatePlayerData")
	fun UpdatePlayerData(@RequestBody PlayerData: playerdata)
	{
		PlayerDataRepo.UpdateData(PlayerData.email, PlayerData.userpassword, PlayerData.isvalid, PlayerData.pid, PlayerData.health, PlayerData.XCoord,
			PlayerData.YCoord, PlayerData.ZCoord)
	}
}

@Entity
class playerdata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var Id: Long = 0
	var email: String = ""
	var userpassword: String = ""
	var isvalid: Boolean = false
	var pid: Int = -1
	var health: Float = 100.0f
	var XCoord: Float = 0.0f
	var YCoord: Float = 0.0f
	var ZCoord: Float = 0.0f

	fun comparePassword(password: String): Boolean {
		return password == this.userpassword
	}

}