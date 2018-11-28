package me.blok601.nightshadeuhc.commands.extras;

import com.nightshadepvp.core.Rank;
import me.blok601.nightshadeuhc.commands.UHCCommand;
import me.blok601.nightshadeuhc.entity.MConf;
import me.blok601.nightshadeuhc.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Blok on 7/9/2018.
 */
public class SetSpawnCommand implements UHCCommand {
    @Override
    public String[] getNames() {
        return new String[]{
                "setspawn"
        };
    }

    @Override
    public void onCommand(CommandSender s, Command cmd, String l, String[] args) {
        Player p = (Player) s;
        MConf.get().updateSpawnLocation(p.getLocation());
        p.sendMessage(ChatUtils.message("&eYou have set the spawn location!"));
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    @Override
    public Rank getRequiredRank() {
        return Rank.ADMIN;
    }

    @Override
    public boolean hasRequiredRank() {
        return true;
    }
}
