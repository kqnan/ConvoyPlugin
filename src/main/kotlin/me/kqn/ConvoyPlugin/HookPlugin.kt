package me.kqn.ConvoyPlugin

import org.black_ixx.playerpoints.PlayerPoints
import org.black_ixx.playerpoints.PlayerPointsAPI
import org.bukkit.Bukkit

object HookPlugin {
    val  playerPointsAPI:PlayerPointsAPI = PlayerPointsAPI(Bukkit.getPluginManager().getPlugin("PlayerPoints") as PlayerPoints?)

}