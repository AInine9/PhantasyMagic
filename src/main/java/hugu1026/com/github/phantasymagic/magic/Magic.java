package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import java.io.File;

public abstract class Magic {
    private Location magicLocation;

    public Magic(String magicName, Event event, Integer slot, Integer neededMana) {
        if (checkMagicName(magicName) && this.checkActivateEventName(event)) {
            ActivateMagicEvent activateMagicEvent = (ActivateMagicEvent) event;

            if (checkMana(getPlayerMana(activateMagicEvent.getPlayer()), neededMana)) {
                magicLocation = this.getMagicLocation(slot, (activateMagicEvent.getPlayer().getLocation()));

                ActivatedMagic(activateMagicEvent, magicLocation);
                consumeMana(activateMagicEvent.getPlayer(), neededMana);
            } else {
                activateMagicEvent.getPlayer().sendMessage(ChatColor.RED + "マナが足りない！");
            }
        }
    }

    public abstract void ActivatedMagic(ActivateMagicEvent event, Location magicLocation);

    public abstract boolean checkMagicName(String magicName);

    public boolean checkActivateEventName(Event event) {
        if (event instanceof ActivateMagicEvent) {
            return true;
        } else {
            return false;
        }
    }

    public Location getMagicLocation(int slot, Location playerLocation) {
        int addBlockstoForward, addBlockstoSide;
        switch (slot) {
            //how many blocks to extend forward
            //this means that the negative number extends backward
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                addBlockstoForward = 4;
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                addBlockstoForward = 3;
                break;
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                addBlockstoForward = 2;
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                addBlockstoForward = 1;
                break;
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
                addBlockstoForward = 0;
                break;
            default:
                return null;
        }
        switch (slot) {
            //how many blocks to extend to the right
            //this means that the negative number extends to the left
            case 2:
            case 11:
            case 20:
            case 29:
            case 38:
                addBlockstoSide = -2;
                break;
            case 3:
            case 12:
            case 21:
            case 30:
            case 39:
                addBlockstoSide = -1;
                break;
            case 4:
            case 13:
            case 22:
            case 31:
            case 40:
                addBlockstoSide = 0;
                break;
            case 5:
            case 14:
            case 23:
            case 32:
            case 41:
                addBlockstoSide = 1;
                break;
            case 6:
            case 15:
            case 24:
            case 33:
            case 42:
                addBlockstoSide = 2;
                break;
            default:
                    return null;
        }

        Vector vector = playerLocation.getDirection();
        Location originalClone = playerLocation.clone();

        //extend vector forward
        Vector vector1 = vector.multiply(addBlockstoForward);
        //rotate vector clockwise
        originalClone.setYaw(originalClone.getYaw()+90);
        //extend vector to the right
        Vector vector2 = originalClone.getDirection().multiply(addBlockstoSide);

        //add two vectors to player's vector
        this.magicLocation = playerLocation.add(vector1).add(vector2);

        return this.magicLocation;
    }

    public int getPlayerMana(Player player) {
        return PlayerDataUtil.getPlayerMANA(player);
    }

    public boolean checkMana(int playerHavingMana, int neededMana) {
        return playerHavingMana >= neededMana;
    }

    public void consumeMana(Player player, int neededMana) {
        File playerFile = PlayerDataUtil.getPlayerFile(player);
        FileConfiguration playerData = PlayerDataUtil.getPlayerData(player);

        playerData.set("status.mana", PlayerDataUtil.getPlayerMANA(player) - neededMana);
        PlayerDataUtil.savePlayerData(playerFile, playerData, player);

        String playerMana = String.valueOf(PlayerDataUtil.getPlayerMANA(player));
        String playerMaxMana = String.valueOf(PlayerDataUtil.getPlayerMAX_MANA(player));
        String message = playerMana + " / " + playerMaxMana;

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
}
