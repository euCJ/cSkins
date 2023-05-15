package me.imcj.cskin

import com.github.orangemonkey73.MojangAPI.MojangAPI
import com.orangemarshall.mineskins.SkinApplier
import com.orangemarshall.mineskins.SkinData
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class SkinCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Este comando só pode ser executado por um jogador.")
            return true
        }
        if (args.size == 0) {
            sender.sendMessage("Você precisa especificar o nome do jogador.")
            return false
        }
        val playerName = args[0]
        val skinData: SkinData = SkinData.getSkinFromName(playerName)
        if (!skinData.isValid()) {
            sender.sendMessage("A skin para o jogador $playerName não foi encontrada.")
            return true
        }
        val playerUUID: String = MojangAPI.getUUID(playerName)
        SkinApplier.applySkin(playerUUID, skinData)
        sender.sendMessage("A skin para o jogador $playerName foi aplicada com sucesso!")
        return true
    }
}