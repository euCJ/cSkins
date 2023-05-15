package me.imcj.cskin

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CSkin : JavaPlugin() {
    override fun onEnable() {
        // Registra o SkinMenu como listener
        server.pluginManager.registerEvents(SkinMenu(), this)
    }
}