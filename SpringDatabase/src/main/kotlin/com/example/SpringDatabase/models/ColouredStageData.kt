package com.example.SpringDatabase.models

import javax.persistence.*

@Entity
class ColouredStageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var Id: Long = 0
    var email: String = ""
    var bluestagecompletiontime: Int = 0
    var yellowstagecompletiontime: Int = 0
    var redstagecompletiontime: Int = 0

    /*

    @ManyToOne
    @JoinColumn(name = "PlayerEmail")
    var userPlayerData: UserPlayerData? = null

     */
}