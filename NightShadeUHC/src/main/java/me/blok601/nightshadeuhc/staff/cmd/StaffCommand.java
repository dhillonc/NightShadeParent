package me.blok601.nightshadeuhc.staff.cmd;

import com.nightshadepvp.core.Rank;
import me.blok601.nightshadeuhc.UHC;
import me.blok601.nightshadeuhc.commands.CmdInterface;
import me.blok601.nightshadeuhc.entity.UHCPlayer;
import me.blok601.nightshadeuhc.staff.spec.SpecCommand;
import me.blok601.nightshadeuhc.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Blok on 6/19/2017.
 */
public class StaffCommand implements CmdInterface {


    @Override
    public String[] getNames() {
        return new String[]{
                "staff"
        };
    }

    @Override
    public void onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        UHCPlayer uhcPlayer = UHCPlayer.get(p);
        if (uhcPlayer.isStaffMode()) {
            SpecCommand.unSpec(p);
            Bukkit.getOnlinePlayers().forEach(o -> o.showPlayer(p));
            p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
            uhcPlayer.setStaffMode(false);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.chat("/rea");
            UHC.players.add(p.getUniqueId());
            p.sendMessage(ChatUtils.message("&eYou are no longer in staff mode!"));
        } else {
            uhcPlayer.staffMode();
        }
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    @Override
    public Rank getRequiredRank() {
        return Rank.TRIAL;
    }

    @Override
    public boolean hasRequiredRank() {
        return true;
    }
}
