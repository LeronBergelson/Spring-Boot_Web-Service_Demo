package com.example.SpringDatabase.repositories

import com.example.SpringDatabase.models.ColouredStageData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ColouredCourseRepository: JpaRepository<ColouredStageData, Long> {


    // Query to retrieve all data corresponding to specific user
    @Query(value = "Call FindAllPlayerByEmail(:Email)", nativeQuery = true)
    fun findByEmail(@Param("Email")Email: String): ColouredStageData

}