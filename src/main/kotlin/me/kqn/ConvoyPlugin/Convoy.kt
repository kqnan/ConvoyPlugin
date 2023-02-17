package me.kqn.ConvoyPlugin

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.Location
import org.bukkit.entity.Player
import taboolib.module.kether.KetherShell
import taboolib.module.kether.runKether
import java.util.*
import kotlin.collections.HashMap

/**ÑºïÚÔËÊäÈÎÎñ
 * */
data class Convoy(var id: Int, var rarity: Rarity, var position: Location, var health: Double,var convoyEntity: ConvoyEntity,var target:Location,var escort: Player)
{

     private var updateTime:Long=System.currentTimeMillis()
    fun isOutDate():Boolean{
        return (System.currentTimeMillis()-updateTime)>=3*60*1000
    }
    fun update(){
        updateTime=System.currentTimeMillis()
    }

    fun failConvoy() {

    }
    fun successConvoy(){

    }
}