package me.kqn.ConvoyPlugin

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

object ConvoyPlugin : Plugin() {

    override fun onEnable() {
        info("Successfully running ExamplePlugin!")
    }
}