package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.event.Event;

public class Fire extends Magic {

    public Fire(String magicName, Event event) {
        super(magicName, event);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event) {
        event.getPlayer().sendMessage("fire occurred");
    }

    @Override
    public boolean checkMagicName(String magicName) {
        if (magicName.equals("ファイヤ")) {
            return true;
        } else {
            return false;
        }
    }
}
