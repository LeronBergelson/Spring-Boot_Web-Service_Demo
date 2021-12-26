package com.example.SpringDatabase.controllers
import com.example.SpringDatabase.models.UserPlayerData
import com.example.SpringDatabase.repositories.PlayerDataRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/PlayerData")
class PlayerDataRestController(val PlayerDataRepo: PlayerDataRepository)
{
    @GetMapping()
    fun GetAllPlayerEntries() = PlayerDataRepo.findAll() // Return all players

    @GetMapping("/{PID}")
    fun GetPID(@PathVariable(value = "PID") PID: Int) : UserPlayerData
    {
        var PData = PlayerDataRepo.GetByPID(PID)
        return PData // Returns players with matching PID to the one passed into the URL
    }

    @PostMapping("register")
    fun SavePlayerData(@RequestBody PlayerData: UserPlayerData){
        PlayerDataRepo.save(PlayerData) // Adds players to the database
    }

    @PostMapping("login")
    fun login(@RequestBody PlayerData: UserPlayerData): ResponseEntity<Any> {
        val player = this.PlayerDataRepo.findByEmail(PlayerData.email)
            ?: return ResponseEntity.badRequest().body("User not found!") // Checks if user sends in request to find player that does not exist

        if(!player.comparePassword(PlayerData.userpassword)){
            return ResponseEntity.badRequest().body("Invalid Password!") // Checks if user sent in email matches stored password
        }

        return ResponseEntity.ok(player) // If login request is successful return player data
    }


    @PutMapping("updatePlayerData")
    fun UpdatePlayerData(@RequestBody PlayerData: UserPlayerData)
    {
        PlayerDataRepo.UpdateData(PlayerData.email, PlayerData.userpassword, PlayerData.isvalid, PlayerData.pid, PlayerData.health, PlayerData.XCoord,
            PlayerData.YCoord, PlayerData.ZCoord, PlayerData.bluestageattempts, PlayerData.yellowstageattempts, PlayerData.redstageattempts) // Updates player data with values passed in
    }
}