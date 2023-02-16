package me.kqn.ConvoyPlugin

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Zombie
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submit
import taboolib.common.platform.function.submitAsync
import taboolib.module.ai.navigationMove
import java.util.*

object ConvoyPlugin : Plugin() {
    private val convos = mutableListOf<Convoy>()
    private val players = mutableMapOf<UUID, PlayerInfo>()


    override fun onEnable() {

     
    }

    override fun onDisable() {


    }
    private fun updateConvoy() {
        // 遍历所有押送任务，更新生物和玩家状态
        for (convo in convos) {
            if (convo.health <= 0) {
                // 生物已经死亡，跳过
                continue
            }
            val playersWithConvo = players.filterValues { it.convoy?.id == convo.id }

            if (playersWithConvo.isEmpty()) {
                // 没有玩家正在押送该镖车，跳过
                continue
            }

            for ((_, playerInfo) in playersWithConvo) {
                val player = playerInfo.player

//                if (player.isDead || !player.isValid || player.location.distance(convo.position) > convo.rarity) {
//                    // 玩家死亡、不在镖车附近或者不在主城范围内，押送失败
//                    failConvoy(playerInfo)
//                    continue
//                }

                // 每5秒扣除一次血量，最多扣除10点
                if (player.world.time % 100 == 0L) {
                    convo.health -= minOf(playerInfo.damageDealt, 10.0)
                }

                // 更新玩家状态
                playerInfo.convoy = convo
                playerInfo.damageDealt = 0.0
            }
        }
    }
    private fun failConvoy(playerInfo: PlayerInfo) {
        val convoy = playerInfo.convoy
        if (convoy != null) {
          //  convo.health = 0.0
          //  convo.position.world.spawn(convoy.position, Creeper::class.java).health = 0.0
        }
        playerInfo.convoy = null
        playerInfo.damageDealt = 0.0
    }

}