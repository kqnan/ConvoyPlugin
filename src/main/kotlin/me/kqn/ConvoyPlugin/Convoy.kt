package me.kqn.ConvoyPlugin

import org.bukkit.Location

data class Convoy(var id: Int, var rarity: Rarity, var position: Location, var health: Double)
