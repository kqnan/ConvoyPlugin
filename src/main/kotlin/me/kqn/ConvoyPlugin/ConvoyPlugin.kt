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
    private val players = mutableMapOf<UUID, PlayerInfo>()


    override fun onEnable() {

    }

    override fun onDisable() {


    }
    @SubscribeEvent
    fun playerLogin(event:PlayerLoginEvent){
        players[event.player.uniqueId] = PlayerInfo(event.player,null,0.0,false)
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
        // ��������Ѻ�����񣬸�����������״̬
        for (convo in convos) {
            if (convo.health <= 0) {
                // �����Ѿ�����������
                continue
            }
            val playersWithConvo = players.filterValues { it.convoy?.id == convo.id }

            if (playersWithConvo.isEmpty()) {
                // û���������Ѻ�͸��ڳ�������
                continue
            }

            for ((_, playerInfo) in playersWithConvo) {
                val player = playerInfo.player

                if (player.isDead || !player.isValid || player.location.distance(convo.position) > convo.rarity.distance) {
                    // ��������������ڳ��������߲������Ƿ�Χ�ڣ�Ѻ��ʧ��
                    failConvoy(playerInfo)
                    continue
                }

                // ÿ5��۳�һ��Ѫ�������۳�10��
                if (player.world.time % 100 == 0L) {
                    convo.health -= minOf(playerInfo.damageDealt, 10.0)
                }

                // �������״̬
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