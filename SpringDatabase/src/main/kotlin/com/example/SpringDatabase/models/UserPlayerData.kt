package com.example.SpringDatabase.models

import javax.persistence.*

@Entity
class UserPlayerData {
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
    var bluestageattempts: Int = 0
    var yellowstageattempts: Int = 0
    var redstageattempts: Int = 0

    /*
    @OneToMany(mappedBy = "userPlayerData", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    lateinit var dataEntries: List<ColouredStageData>
    */

    fun comparePassword(password: String): Boolean {
        return password == this.userpassword
    }

}