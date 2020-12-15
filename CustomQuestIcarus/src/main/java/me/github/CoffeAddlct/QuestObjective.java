package me.github.CoffeAddlct;

import me.blackvein.quests.CustomObjective;
import me.blackvein.quests.Quest;
import me.blackvein.quests.Quests;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.material.Crops;
import java.util.UUID;

public class QuestObjective extends CustomObjective implements Listener {
    Quests qp = (Quests)Bukkit.getServer().getPluginManager().getPlugin("Quests");
    public QuestObjective() {

        this.setName("Harvest Objective by Icarus");
        this.setAuthor("Icarus");
        this.setShowCount(true);
        this.setCountPrompt("Insert the number of seeds required to complete the quest:");
        this.setDisplay(ChatColor.GREEN +"You harvested "+ "%count%" + "seeds");
    }

    @EventHandler
    public void onHarvest(BlockBreakEvent e){
        Block harvestedBlock = e.getBlock();
        BlockData blockData = harvestedBlock.getBlockData();
        if(!(blockData instanceof Ageable)) return;
        Ageable ageable = (Ageable) blockData;
        int age = ageable.getAge();
        if(age != 7) return;

        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        for(Quest quest : qp.getQuester(uuid).getCurrentQuests().keySet()) {
            incrementObjective(player, this, 1, quest);
        }


    }
}