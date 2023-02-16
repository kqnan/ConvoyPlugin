package me.kqn.ConvoyPlugin.Event

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.entity.Player
import taboolib.common.platform.event.ProxyEvent

class ConvoyEntityDeathEvent (val convoyEntity: ConvoyEntity,val killer: Player):ProxyEvent() {
    override val allowCancelled: Boolean
        get() = false
}