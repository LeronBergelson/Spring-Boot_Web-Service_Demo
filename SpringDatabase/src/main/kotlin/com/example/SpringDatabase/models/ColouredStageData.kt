package com.example.SpringDatabase.models

import javax.persistence.*

@Entity
class ColouredStageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var Id: Long = 0
    var email: String = ""
    var bluestagecompletiontime: Long = 0
    var yellowstagecompletiontime: Long = 0
    var redstagecompletiontime: Long = 0

    /*

    @ManyToOne
    @JoinColumn(name = "PlayerEmail")
    var userPlayerData: UserPlayerData? = null

     */
}