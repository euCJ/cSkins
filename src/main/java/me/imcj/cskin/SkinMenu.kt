package me.imcj.cskin

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta

@Suppress("DEPRECATION")
class SkinMenu : Listener {
    private val resetItem: ItemStack = createResetItem()
    private val skinItem: ItemStack = createSkinItem()
    private val nickItem: ItemStack = createNickItem()
    private val tagItem: ItemStack = createTagItem()
    private val medalItem: ItemStack = createMedalItem()
    private val skinMenuItem: ItemStack = createSkinMenuItem()

    @EventHandler
    fun onCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message == "/skin") {
            openSkinMenu(event.player)
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title != "Sua skin") {
            return
        }

        event.isCancelled = true

        when (event.currentItem) {
            nickItem -> {
                val player = event.whoClicked as Player
                val playerName = player.name // obter o nome do jogador
                player.sendMessage("")
                player.sendMessage("${ChatColor.RED}${ChatColor.BOLD}FAKE ➜ ${ChatColor.WHITE}Função ainda está em desenvolvimento.")
                player.sendMessage("")
                player.closeInventory()
                val console = Bukkit.getServer().consoleSender // cria um CommandSender personalizado
                Bukkit.dispatchCommand(console,"tm amessage $playerName &cAinda em Desenvolvimento.")
            }
            resetItem -> {
                val player = event.whoClicked as Player
                val playerName = player.name // obter o nome do jogador
                player.sendMessage("")
                player.sendMessage("${ChatColor.AQUA}${ChatColor.BOLD}SKIN ➜ ${ChatColor.WHITE}Sua skin foi ${ChatColor.RED}REMOVIDA${ChatColor.WHITE}.")
                player.sendMessage("")
                player.closeInventory()
                val console = Bukkit.getServer().consoleSender // cria um CommandSender personalizado
                Bukkit.dispatchCommand(console, "skin clear $playerName") // executa o comando "skin clear nickname" no console
            }
            skinItem -> {
                val player = event.whoClicked as Player
                val playerName = player.name // obter o nome do jogador
                player.sendMessage("")
                player.sendMessage("${ChatColor.GOLD}${ChatColor.BOLD}SKIN ➜ ${ChatColor.WHITE}Utilize o comando:${ChatColor.YELLOW} /skin (nickname/url)${ChatColor.WHITE}.")
                player.sendMessage("")
                player.closeInventory()
                val console = Bukkit.getServer().consoleSender // cria um CommandSender personalizado

                Bukkit.dispatchCommand(console, "tm message $playerName &e&lSKIN\n&7Mais informações no chat.",)

            }
            tagItem -> {
                val player = event.whoClicked as Player
                val playerName = player.name // obter o nome do jogador
                val console = Bukkit.getServer().consoleSender // cria um CommandSender personalizado
                Bukkit.dispatchCommand(console, "sudo $playerName tag") // executa o comando "sudo nickname tags" no console

                player.sendMessage("")
                player.sendMessage("${ChatColor.GOLD}${ChatColor.BOLD}TAGS ➜ ${ChatColor.WHITE}Você está vendo o Menu de tag!")
                player.sendMessage("")
                player.closeInventory()
            }
            skinMenuItem -> {
                val player = event.whoClicked as Player
                val playerName = player.name // obter o nome do jogador
                val console = Bukkit.getServer().consoleSender // cria um CommandSender personalizado
                Bukkit.dispatchCommand(console, "sudo $playerName skins") // executa o comando "sudo nickname tags" no console

                player.sendMessage("")
                player.sendMessage("${ChatColor.GOLD}${ChatColor.BOLD}SKINS ➜ ${ChatColor.WHITE}Você está vendo o Menu de Skins!")
                player.closeInventory()
            }
            medalItem -> {
                val player = event.whoClicked as Player
                val playerName = player.name // obter o nome do jogador
                val console = Bukkit.getServer().consoleSender // cria um CommandSender personalizado
                Bukkit.dispatchCommand(console, "sudo $playerName medal") // executa o comando "sudo nickname tags" no console

                player.sendMessage("")
                player.sendMessage("${ChatColor.GOLD}${ChatColor.BOLD}MEDALHAS ➜ ${ChatColor.WHITE}Você está vendo o Menu de medalha!")
                player.sendMessage("")
                player.closeInventory()
                }

            else -> return
        }
    }

    private fun openSkinMenu(player: Player) {
        val inventory: Inventory = Bukkit.createInventory(null, 54, "Sua skin")
        inventory.setItem(4, createCurrentSkinItem(player))
        inventory.setItem(13, nickItem)
        inventory.setItem(28, skinMenuItem)
        inventory.setItem(30, skinItem)
        inventory.setItem(32, tagItem)
        inventory.setItem(34, medalItem)
        inventory.setItem(49, resetItem)
        player.openInventory(inventory)
    }

    private fun createSkinMenuItem(): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.BOOKSHELF)
        val itemMeta: ItemMeta = itemStack.itemMeta!!
        itemMeta.setDisplayName("${ChatColor.GOLD}➜ Menu de Skins")
        itemMeta.lore = listOf("",
            "${ChatColor.GRAY}Veja o MENU de skin disponíveis e",
            "${ChatColor.GRAY}selecione uma para usar no servidor.",
            "",
            "${ChatColor.YELLOW}Clique para selecionar.",)
        itemStack.itemMeta = itemMeta
        return itemStack
    }
    private fun createMedalItem(): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.NETHER_STAR)
        val itemMeta: ItemMeta = itemStack.itemMeta!!
        itemMeta.setDisplayName("${ChatColor.GOLD}➜ Medalhas")
        itemMeta.lore = listOf("",
            "${ChatColor.GRAY}Selecione e exiba medalahas únicas e",
            "${ChatColor.GRAY}exclusivas para outros jogadores.",
            "",
            "${ChatColor.YELLOW}Clique para selecionar.",)
        itemStack.itemMeta = itemMeta
        return itemStack
    }
    private fun createTagItem(): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.NAME_TAG)
        val itemMeta: ItemMeta = itemStack.itemMeta!!
        itemMeta.setDisplayName("${ChatColor.GOLD}➜ Tags")
        itemMeta.lore = listOf("",
            "${ChatColor.GRAY}Veja as Tags que você possui e",
            "${ChatColor.GRAY}selecione uma para usar no servidor.",
            "",
            "${ChatColor.YELLOW}Clique para selecionar.",)
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    private fun createCurrentSkinItem(player: Player): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.PLAYER_HEAD)
        val itemMeta: SkullMeta = itemStack.itemMeta as SkullMeta
        itemMeta.owningPlayer = player
        itemMeta.setDisplayName("${ChatColor.GOLD}➜ Sua skin: ${player.name}")
        itemMeta.lore = listOf("", "${ChatColor.GRAY}Fonte: Mojang")
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    private fun createResetItem(): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.RED_DYE)
        val itemMeta: ItemMeta = itemStack.itemMeta!!
        itemMeta.setDisplayName("${ChatColor.RED}➜ Remover skin")
        itemMeta.lore = listOf("",
            "${ChatColor.YELLOW}Clique para remover sua skin atual.")
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    private fun createSkinItem(): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.ANVIL)
        val itemMeta: ItemMeta = itemStack.itemMeta!!
        itemMeta.setDisplayName("${ChatColor.GOLD}➜ Customizar Skin")
        itemMeta.lore = listOf("",
            "${ChatColor.GRAY}Escolha uma skin customizada",
            "${ChatColor.GRAY}baseado em um nickname",
            "",
            "${ChatColor.YELLOW}Clique para selecionar!",
            "",
            "${ChatColor.RED}Requer ${ChatColor.DARK_GREEN}[Ajudante]${ChatColor.RED} ou superior.")
        itemStack.itemMeta = itemMeta
        return itemStack
    }
    private fun createNickItem(): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.LEGACY_EMPTY_MAP)
        val itemMeta: ItemMeta = itemStack.itemMeta!!
        itemMeta.setDisplayName("${ChatColor.GOLD}➜ Nick personalizado")
        itemMeta.lore = listOf("",
            "${ChatColor.GRAY}Escolha qualquer Nick para",
            "${ChatColor.GRAY}usar dentro do servidor através",
            "${ChatColor.GRAY}do ${ChatColor.GOLD}/fake${ChatColor.GRAY}.",
            "",
            "${ChatColor.YELLOW}Clique para selecionar!",
            "",
            "${ChatColor.RED}Requer ${ChatColor.DARK_GREEN}[Ajudante]${ChatColor.RED} ou superior.")
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}