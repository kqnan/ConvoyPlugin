package me.kqn.ConvoyPlugin

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerLoginEvent
import taboolib.common.platform.Plugin
import taboolib.common.platform.command.command
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submit
import taboolib.common.platform.function.submitAsync
import taboolib.module.ai.navigationMove
import taboolib.module.kether.KetherShell
import taboolib.platform.util.attacker
import java.util.*

object ConvoyPlugin : Plugin() {
    private val convos = mutableListOf<Convoy>()


    override fun onEnable() {
        command(name="Convoy"){
            literal("kether"){
                execute<Player>{
                    sender, context, argument ->
                    info(argument)
                    KetherShell.eval(source =argument.replace("kether",""),vars=KetherShell.VariableMap(Pair("pos",sender.location),Pair("p",sender)))
                }
            }
            literal("start"){
                execute<Player>{
                    sender, context, argument ->
                    
                }
            }
        }
        submit (period = 20){ updateConvoy() }
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
        // ��������Ѻ������
        for (convo in convos) {
            //�������ѹ����ˣ����ܾ�û������
            if(convo.isOutDate()){
                convo.failConvoy()
                continue
            }
            //�����˹�����
            if(convo.convoyEntity.currentHealth<=0){
                convo.failConvoy()
                continue
            }
            //��û�л����ߣ������߲��ڷ�Χ
            if(!convo.escort.isOnline||convo.escort.isDead||convo.escort.location.distance(convo.convoyEntity.entity.location)>5){
                convo.convoyEntity.stopNavigation()//ֹͣ�ж�
                continue
            }
            convo.update()//��������




        }
    }


}