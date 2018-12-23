package me.blok601.nightshadeuhc.task;

import me.blok601.nightshadeuhc.UHC;
import me.blok601.nightshadeuhc.entity.UHCPlayer;
import me.blok601.nightshadeuhc.entity.UHCPlayerColl;
import me.blok601.nightshadeuhc.entity.object.GameState;
import me.blok601.nightshadeuhc.manager.GameManager;
import me.blok601.nightshadeuhc.util.ActionBarUtil;
import me.blok601.nightshadeuhc.util.ChatUtils;
import me.blok601.nightshadeuhc.util.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by Blok on 12/22/2018.
 */
public class GameCountdownTask extends BukkitRunnable {

    int counter = 180;
    UHC uhc;

    public GameCountdownTask(UHC uhc) {
        this.uhc = uhc;
    }

    @Override
    public void run() {
        if (counter <= 0) {
            this.cancel();
            ArrayList<Player> valid = new ArrayList<>();
            UHCPlayer gp;
            for (Player ps : Bukkit.getOnlinePlayers()) {
                gp = UHCPlayer.get(ps.getUniqueId());
                if (!gp.isSpectator()) {
                    valid.add(ps);
                    if (UHC.players.contains(ps.getUniqueId())) continue;
                    UHC.players.add(ps.getUniqueId());
                }
            }

            GameManager.get().getWorld().setTime(20);
            ChatUtils.setChatFrozen(true);
            Bukkit.broadcastMessage(ChatUtils.message("&3Use /helpop or message any online staff members if you need help!"));
            Freeze.start();
            UHCPlayerColl.get().getAllOnline().stream().filter(UHCPlayer::isInArena).forEach(UHCPlayer::leaveArena);
            GameState.setState(GameState.STARTING);
            Bukkit.getOnlinePlayers().forEach(o -> {
                ActionBarUtil.sendActionBarMessage(o, "§5The scatter is beginning....");
            });
            new ScatterTask(valid, GameManager.get().getWorld(), GameManager.get().getSetupRadius(), GameManager.get().getHost(), GameManager.get().getFinalHealTime(), GameManager.get().getPvpTime(), GameManager.get().getBorderTime(), GameManager.get().isIsTeam(), GameManager.get().getFirstShrink(), GameManager.get().getMeetupTime()).runTaskTimer(UHC.get(), 0, 4);
            return;
        }

        if (counter % 60 == 0) {
            Bukkit.broadcastMessage(ChatUtils.message("&eScatter will begin in &b" + (counter / 60) + " &eminute&8(&es&8)..."));
        }
        counter--;

        Bukkit.getOnlinePlayers().forEach(o -> {
            ActionBarUtil.sendActionBarMessage(o, "§5Scatter§8» " + get(counter), 1, uhc);
        });

    }

    private String get(int i) {
        int m = i / 60;
        int s = i % 60;

        return "§3" + m + "§5m§3" + s + "§5s";
    }

}
