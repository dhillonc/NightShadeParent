package me.blok601.nightshadeuhc.scenario;

import me.blok601.nightshadeuhc.util.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class VeinminerScenario extends Scenario{

    public VeinminerScenario() {
        super("Veinminer", "When an ore is mined, the other ores are broken around it", new ItemBuilder(Material.DIAMOND_PICKAXE).name("Veinminer").make());
    }

    @EventHandler
    public void on(BlockBreakEvent e){
        Player p = e.getPlayer();
        Block block = e.getBlock();

        if(!isEnabled()){
            return;
        }

        if(!p.isSneaking()){
            return;
        }

        if(block.getType() == Material.COAL_ORE){
            ArrayList<Block> blocks = getBlocks(block, 2, Material.COAL_ORE);
            for (Block b : blocks){
                b.setType(Material.AIR);
            }

            if(blocks.size() == 0) return;
            p.getInventory().addItem(new ItemStack(Material.COAL, blocks.size()));
            ((ExperienceOrb)block.getWorld().spawn(block.getLocation(), ExperienceOrb.class)).setExperience(2*blocks.size());
        }

        if(block.getType() == Material.IRON_ORE){
            ArrayList<Block> blocks = getBlocks(block, 2, Material.IRON_ORE);
            for (Block b : blocks){
                b.setType(Material.AIR);
            }

            if(blocks.size() == 0) return;
            if(ScenarioManager.getScen("CutClean").isEnabled()){
                p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, blocks.size()));
            }else{
                p.getInventory().addItem(new ItemStack(Material.IRON_ORE, blocks.size()));
            }

            ((ExperienceOrb)block.getWorld().spawn(block.getLocation(), ExperienceOrb.class)).setExperience(2*blocks.size());
        }

        if(block.getType() == Material.GOLD_ORE){
            ArrayList<Block> blocks = getBlocks(block, 2, Material.GOLD_ORE);
            for (Block b : blocks){
                b.setType(Material.AIR);
            }

            if(blocks.size() == 0) return;

            if(ScenarioManager.getScen("CutClean").isEnabled()){
                p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, blocks.size()));
            }else{
                p.getInventory().addItem(new ItemStack(Material.GOLD_ORE, blocks.size()));
            }

            ((ExperienceOrb)block.getWorld().spawn(block.getLocation(), ExperienceOrb.class)).setExperience(2*blocks.size());
        }

        if(block.getType() == Material.DIAMOND_ORE){
            ArrayList<Block> blocks = getBlocks(block, 2, Material.DIAMOND_ORE);
            for (Block b : blocks){
                b.setType(Material.AIR);
            }

            if(blocks.size() == 0) return;
            p.getInventory().addItem(new ItemStack(Material.DIAMOND, blocks.size()));
            ((ExperienceOrb)block.getWorld().spawn(block.getLocation(), ExperienceOrb.class)).setExperience(2*blocks.size());
        }

        if(block.getType() == Material.REDSTONE_ORE){
            ArrayList<Block> blocks = getBlocks(block, 2, Material.REDSTONE_ORE);
            for (Block b : blocks){
                b.setType(Material.AIR);
            }

            if(blocks.size() == 0) return;
            p.getInventory().addItem(new ItemStack(Material.REDSTONE, blocks.size()*4));
            ((ExperienceOrb)block.getWorld().spawn(block.getLocation(), ExperienceOrb.class)).setExperience(2*blocks.size());
        }

        if(block.getType() == Material.EMERALD_ORE){
            ArrayList<Block> blocks = getBlocks(block, 2, Material.EMERALD_ORE);
            for (Block b : blocks){
                b.setType(Material.AIR);
            }

            if(blocks.size() == 0) return;
            p.getInventory().addItem(new ItemStack(Material.EMERALD, blocks.size()));
            ((ExperienceOrb)block.getWorld().spawn(block.getLocation(), ExperienceOrb.class)).setExperience(2*blocks.size());
        }

    }


    private ArrayList<Block> getBlocks(Block start, int radius, Material filter){
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++){
            for(double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++){
                for(double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++){
                    Location loc = new Location(start.getWorld(), x, y, z);
                    if(loc.getBlock().getType() == filter)
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;

    }
}
