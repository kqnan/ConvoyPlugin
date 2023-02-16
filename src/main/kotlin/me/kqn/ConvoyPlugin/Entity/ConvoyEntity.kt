package me.kqn.ConvoyPlugin.Entity

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import taboolib.common.platform.function.adaptLocation
import taboolib.module.ai.clearGoalAi
import taboolib.module.ai.clearTargetAi
import taboolib.module.ai.navigationMove

 class ConvoyEntity {
    private val entity:LivingEntity

    constructor(entity: LivingEntity) {
        this.entity = entity
    }

    fun StartNavigation(location:Location){
        entity.navigationMove(location)
    }
    fun stopNavigation(){
        entity.clearGoalAi()
        entity.clearTargetAi()
    }


}