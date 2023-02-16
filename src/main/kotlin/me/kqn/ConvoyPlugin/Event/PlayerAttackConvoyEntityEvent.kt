package me.kqn.ConvoyPlugin.Event

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.entity.Player
import org.bukkit.event.Event

import taboolib.common.platform.event.ProxyEvent
import taboolib.platform.type.BukkitProxyEvent

class PlayerAttackConvoyEntityEvent(val player: Player,val convoyEntity: ConvoyEntity): ProxyEvent () {
    override val allowCancelled: Boolean
        get() = true

}