package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.event.Event;

public class Freeze extends Magic {

    public Freeze(String magicName, Event event) {
        super(magicName, event);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event) {
        event.getPlayer().sendMessage("freeze occurred");
    }

    @Override
    public boolean checkMagicName(String magicName) {
        if (magicName.equals("フリーズ")) {
            return true;
        } else {
            return false;
        }
    }
}
