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

	@Query(value = "Call UpdatePlayerData(:Id, :IsValid, :PID, :Health, :XCoord, :YCoord, :ZCoord)", nativeQuery = true)
	fun UpdateData(@Param("Id")Id: Long, @Param("IsValid")IsValid: Boolean, @Param("PID")PID: Int, @Param("Health")Health: Float,
				   @Param("XCoord")XCoord: Float, @Param("YCoord")YCoord: Float, @Param("ZCoord")ZCoord: Float) : playerdata

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

	@PostMapping()
	fun SavePlayerData(@RequestBody PlayerData: playerdata){
		PlayerDataRepo.save(PlayerData)
	}

	@PutMapping()
	fun UpdatePlayerData(@RequestBody PlayerData: playerdata)
	{
		PlayerDataRepo.UpdateData(PlayerData.Id, PlayerData.isvalid, PlayerData.pid, PlayerData.health, PlayerData.XCoord,
			PlayerData.YCoord, PlayerData.ZCoord)
	}
}

@Entity
class playerdata(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val Id: Long = 0, var isvalid: Boolean = false, var pid: Int = -1, var health: Float = 100.0f, var XCoord: Float = 0.0f, var YCoord: Float = 0.0f, var ZCoord: Float = 0.0f
)