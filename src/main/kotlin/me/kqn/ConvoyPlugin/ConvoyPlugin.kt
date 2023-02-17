package me.kqn.ConvoyPlugin

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerLoginEvent
import taboolib.common.platform.Plugin
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submit
import taboolib.common.platform.function.submitAsync
import taboolib.module.ai.navigationMove
import taboolib.platform.util.attacker
import java.util.*

object ConvoyPlugin : Plugin() {
    private val convos = mutableListOf<Convoy>()


    override fun onEnable() {

    }

    override fun onDisable() {


    }

    @SubscribeEvent
    fun playerAttack(event: EntityDamageByEntityEvent){
        if(!event.isCancelled&&event.attacker is Player){
            convos.forEach {
                if(event.entity===it.convoyEntity.entity){
                    event.isCancelled=true
                    it.convoyEntity.attack(event.attacker as Player)
                }
            }
        }
    }
    private fun updateConvoy() {
        // 遍历所有押送任务
        for (convo in convos) {
            //若任务已过期了，即很久没更新了
            if(convo.isOutDate()){
                convo.failConvoy()
                continue
            }
            //若搬运工死了
            if(convo.convoyEntity.currentHealth<=0){
                convo.failConvoy()
                continue
            }
            //若没有护送者，或护送者不在范围
            if(!convo.escort.isOnline||convo.escort.isDead||convo.escort.location.distance(convo.convoyEntity.entity.location)>5){
                convo.convoyEntity.stopNavigation()//停止行动
                continue
            }
            convo.update()//更新任务




        }
    }


}