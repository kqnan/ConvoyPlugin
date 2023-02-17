package me.kqn.ConvoyPlugin

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

object ConfigFile {
    @Config(autoReload = true)
    lateinit var config:Configuration
    @ConfigNode(value="OnAttack")
    lateinit var OnAttack:String
    @ConfigNode(value="OnSuccess")
    lateinit var OnSuccess:String
    @ConfigNode(value="OnFailed")
    lateinit var OnFailed:String
}