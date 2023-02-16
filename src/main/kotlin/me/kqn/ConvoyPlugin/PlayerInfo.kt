package me.kqn.ConvoyPlugin

import org.bukkit.entity.Player

data class PlayerInfo(var player: Player, var convoy: Convoy?, var damageDealt: Double, var isDead: Boolean)
