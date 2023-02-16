package me.kqn.ConvoyPlugin

import me.kqn.ConvoyPlugin.Entity.ConvoyEntity
import org.bukkit.Location

data class Convoy(var id: Int, var rarity: Rarity, var position: Location, var health: Double,var convoyEntity: ConvoyEntity)
