package me.blok601.nightshadeuhc.scenario;

import com.nightshadepvp.core.Rank;
import me.blok601.nightshadeuhc.UHC;
import me.blok601.nightshadeuhc.commands.CmdInterface;
import me.blok601.nightshadeuhc.utils.ChatUtils;
import me.blok601.nightshadeuhc.utils.ItemBuilder;
import me.blok601.nightshadeuhc.utils.PagedInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Blok on 3/28/2017.
 */
public class ScenarioManager implements CmdInterface{


    private static ArrayList<Scenario> scenarios = new ArrayList<>();

    public void setup(){
        addScen(new AnonymousScenario());
        addScen(new AssaultAndBatteryScenario());
        addScen(new AurophobiaScenario());
        addScen(new BackpackScenario());
        addScen(new BarebonesScenario());
        addScen(new BatsScenario());
        addScen(new BedBombScenario());
        addScen(new BenchBlitzScenario());
        addScen(new BestPvEScenario());
        addScen(new BetaZombiesScenario());
        addScen(new BleedingSweetsScenario());
        addScen(new BlockedScenario());
        addScen(new BloodDiamondsScenario());
        addScen(new BloodStoneScenario());
        addScen(new BomberScenario());
        addScen(new BowlessScenario());
        addScen(new ChickenScenario());
        addScen(new ColdWeaponsScenario());
        addScen(new CreeperPongScenario());
        addScen(new CrippleScenario());
        addScen(new CutCleanScenario());
        addScen(new DepthsScenario());
        addScen(new DiamondLessScenario());
        addScen(new DragonRushScenario());
        addScen(new EnchantedDeathScenario());
        addScen(new EntropyScenario());
        addScen(new EveryRoseScenario());
        addScen(new FastGetawayScenario());
        addScen(new FeistyBoysScenario());
        addScen(new FirelessScenario());
        //addScen(new FlowerPowerScenario());
        addScen(new FurnaceDeathScenario());
        addScen(new GapZapScenario());
        addScen(new GigadrillScenario());
        addScen(new GoldLessScenario());
        addScen(new GoneFishinScenario());
        addScen(new HasteyBoysScenario());
        addScen(new HobbitScenario());
        addScen(new InfiniteEnchanterScenario());
        addScen(new KingsScenario());
        addScen(new LootCrateScenario());
        addScen(new NoCleanScenario());
        addScen(new NoFurnaceScenario());
        addScen(new NoFallScenario());
        addScen(new OneHealScenario());
        addScen(new OneHundredHeartsScenario());
        addScen(new PermaKillScenario());
        addScen(new PuppyPowerScenario());
        addScen(new PuppyPlusScenario());
        addScen(new RewardingLongShotsScenario());
        addScen(new RiskyRetrievalScenario());
        addScen(new Scenario("Rush", "The game progresses quicker", new  ItemStack(Material.COMPASS, 1)));
        addScen(new SkycleanScenario());
        addScen(new SkyhighScenario());
        addScen(new SlutCleanScenario());
        addScen(new SoupScenario());
        addScen(new SoupPlusScenario());
        addScen(new StockUpScenario());
        addScen(new SuperheroesScenario());
        addScen(new SwitcherooScenario());
        addScen(new TimberScenario());
        addScen(new TimebombScenario());
        addScen(new TrashOrTreasureScenario());
        addScen(new UltraParanoidScenario());
        addScen(new UnbreakableBoysScenario());
        addScen(new VeinminerScenario());
        addScen(new VillagerMadnessScenario());
        addScen(new WeakestLinkScenario());
        addScen(new WebCageScenario());
    }



    private void addScen(Scenario s){
        scenarios.add(s);
        Bukkit.getPluginManager().registerEvents(s, UHC.get());
    }

    public static Scenario getScen(String name){
        return scenarios.stream().filter(scem -> scem.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
//        for (Scenario s : scenarios){
//            if(name.equalsIgnoreCase(s.getName())){
//                return s;
//            }
//        }
//
//        return null;
    }

    public void openScenarioGUI(Player player){
//        Inventory inv = Bukkit.createInventory(null, 54, ChatUtils.format("&6Scenarios"));
//        inv.clear();
//        for(Scenario scenario :  getScenarios()){
//           ItemStack item = new ItemBuilder(scenario.getItem()).name(scenario.isEnabled() ? ChatUtils.format("&a" + scenario.getName()) : ChatUtils.format("&c" + scenario.getName())).make();
//           inv.addItem(item);
//        }
//
//        player.openInventory(inv);

        ArrayList<ItemStack> items = new ArrayList<>();

        for(Scenario scenario :  getScenarios()){
           ItemStack item = new ItemBuilder(scenario.getItem()).name(scenario.isEnabled() ? ChatUtils.format("&a" + scenario.getName()) : ChatUtils.format("&c" + scenario.getName())).make();
            items.add(item);
        }

        new PagedInventory(items, ChatColor.translateAlternateColorCodes('&', "&6Scenarios"), player);

    }

    public static Scenario getScen(ItemStack itemStack){
        for (Scenario scenario : getScenarios()){
            if(scenario.getItem().equals(itemStack)){
                return scenario;
            }
        }
        return null;
    }


    public static ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public static ArrayList<Scenario> getEnabledScenarios(){
        ArrayList<Scenario> toReturn = new ArrayList<>();
        for (Scenario scenario : getScenarios()){
            if(scenario.isEnabled()){
                toReturn.add(scenario);
            }
        }

        return toReturn;
    }

    @Override
    public String[] getNames() {
        return new String[]{
                "scenario"
        };
    }

    @Override
    public void onCommand(CommandSender s, Command cmd, String l, String[] args) {
        if(s instanceof Player) {
            Player p = (Player) s;
            if(args.length == 0){
                openScenarioGUI(p);
                p.sendMessage(ChatUtils.message("&eOpening Scenario GUI..."));
                return;
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("clear")){
                    ScenarioManager.getEnabledScenarios().forEach(scenario -> scenario.setEnabled(false));
                    p.sendMessage(ChatUtils.message("&eAll scenarios have been disabled!"));
                    return;
                }
            }else {
                if(args[0].equalsIgnoreCase("enable")){
                    StringBuilder builder = new StringBuilder();

                    for (int i = 1; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }

                    String f = builder.toString().trim();


                    Scenario scenario = ScenarioManager.getScen(f);
                    if(scenario == null){
                        p.sendMessage(ChatUtils.message("That scenario couldn't be found!"));
                        return;
                    }

                    scenario.setEnabled(true);
                    p.sendMessage(ChatUtils.message("&aEnabled &e" + scenario.getName() + "!"));
                }else if(args[0].equalsIgnoreCase("disable")){
                    StringBuilder builder = new StringBuilder();

                    for (int i = 1; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }

                    String f = builder.toString().trim();


                    Scenario scenario = ScenarioManager.getScen(f);
                    if(scenario == null){
                        p.sendMessage(ChatUtils.message("That scenario couldn't be found!"));
                        return;
                    }

                    scenario.setEnabled(false);
                    p.sendMessage(ChatUtils.message("&cDisabled &e" + scenario.getName() +"!"));
                }else{
                    p.sendMessage(ChatUtils.message("&cUsage: /scen"));
                    p.sendMessage(ChatUtils.message("&cUsage: /scen <enable/disable> <scenario>"));
                    return;
                }
            }
        }
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    @Override
    public Rank getRequiredRank() {
        return Rank.HOST;
    }

    @Override
    public boolean hasRequiredRank() {
        return true;
    }
}
