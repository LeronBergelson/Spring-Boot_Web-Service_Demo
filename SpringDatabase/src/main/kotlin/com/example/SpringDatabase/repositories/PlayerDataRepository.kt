package com.example.SpringDatabase.repositories

import com.example.SpringDatabase.models.UserPlayerData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface PlayerDataRepository: JpaRepository<UserPlayerData, Long> {

    // Query to retrieve all data for all users with the specific passed in PID.
    @Query(value = "Call GetByPID(:PID)", nativeQuery = true)
    fun GetByPID(@Param("PID")PID: Int): UserPlayerData

    // Update player query
    @Query(value = "Call UpdatePlayerData(:Email, :UserPassword, :IsValid, :PID, :Health, :XCoord, :YCoord, :ZCoord, :bluestageattempts, " +
            ":yellowstageattempts, :redstageattempts)", nativeQuery = true)
    fun UpdateData(@Param("Email")Email: String, @Param("UserPassword")UserPassword: String, @Param("IsValid")IsValid: Boolean,
                   @Param("PID")PID: Int, @Param("Health")Health: Float, @Param("XCoord")XCoord: Float, @Param("YCoord")YCoord: Float,
                   @Param("ZCoord")ZCoord: Float, @Param("bluestageattempts")bluestageattempts: Int, @Param("yellowstageattempts")yellowstageattempts: Int,
                   @Param("redstageattempts")redstageattempts: Int) : UserPlayerData

    // Query to retrieve all data for all users with the specific passed in Email.
    @Query(value = "Call FindByEmail(:Email)", nativeQuery = true)
    fun findByEmail(@Param("Email")Email: String): UserPlayerData?
}