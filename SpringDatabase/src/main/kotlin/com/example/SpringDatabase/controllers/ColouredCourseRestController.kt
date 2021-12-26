package com.example.SpringDatabase.controllers

import com.example.SpringDatabase.models.ColouredStageData
import com.example.SpringDatabase.models.UserPlayerData
import com.example.SpringDatabase.repositories.ColouredCourseRepository
import com.example.SpringDatabase.repositories.PlayerDataRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/ColouredCourseData")
class ColouredCourseRestController(val ColouredCourseData: ColouredCourseRepository) {

    @GetMapping()
    fun GetAllPlayerEntries() = ColouredCourseData.findAll()

    @PostMapping("update")
    fun SavePlayerData(@RequestBody courseData: ColouredStageData){
        ColouredCourseData.save(courseData) // Adds player record to the database (if player exists - foreign key assigned through MYSQL)
    }
}