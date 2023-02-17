package me.kqn.ConvoyPlugin.Entity

import me.kqn.ConvoyPlugin.Event.ConvoyEntityDeathEvent
import me.kqn.ConvoyPlugin.Event.PlayerAttackConvoyEntityEvent
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptLocation
import taboolib.common.util.random
import taboolib.common5.Baffle
import taboolib.module.ai.clearGoalAi
import taboolib.module.ai.clearTargetAi
import taboolib.module.ai.navigationMove
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

class ConvoyEntity {
    var damage=HashMap<UUID,Int>()
    private set
    var entity:LivingEntity
    private set
    var maxDamagePerAttack:Double
        private set
    var damageCooldown:Int
        private  set
    var maxHealth:Int
        private set
    var currentHealth:Int
        private set
    private var baffle:Baffle
     var isMoving:Boolean
        private set
     constructor(entity: LivingEntity,maxHealth:Int,maxDamagePerAttack:Double=10.0,DamageCooldown:Int=5) {
        this.entity = entity
         this.maxDamagePerAttack=maxDamagePerAttack
        this.damageCooldown=DamageCooldown
        this.maxHealth=maxHealth
        currentHealth=maxHealth
         isMoving=false
         baffle= Baffle.of(DamageCooldown.toLong(),TimeUnit.SECONDS)

     }
    fun attack(attacker: Player){
        if(baffle.hasNext()){
            if(PlayerAttackConvoyEntityEvent(attacker,this).call()){
                baffle.next()
                val randomDamage=random(maxDamagePerAttack.toInt())
                currentHealth-= randomDamage
                damage[attacker.uniqueId]=damage.getOrDefault(attacker.uniqueId,0)+ randomDamage
                if(currentHealth<=0){
                    ConvoyEntityDeathEvent(this,attacker).call()
                    stopNavigation()
                }
            }
        }
    }
    fun StartNavigation(location:Location){
        entity.navigationMove(location)
        isMoving=true
    }
    fun stopNavigation(){
        isMoving=false
        entity.clearGoalAi()
        entity.clearTargetAi()
    }


}