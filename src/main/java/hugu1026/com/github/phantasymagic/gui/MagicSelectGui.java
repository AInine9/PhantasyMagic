package hugu1026.com.github.phantasymagic.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MagicSelectGui extends Gui {

    private ItemStack FIRE, FREEZE, EXPLOSION, ACID_RAIN, ICELC_DROP;
    private ItemStack[] magicSet;
    private List<ItemStack> magicSets;
    private Inventory guiSource;
    private int slotSource;

    public MagicSelectGui(Inventory inventory, int slotSource) {
        this.FIRE = super.createItemStack(Material.FIREBALL, ChatColor.YELLOW + "ファイヤ /5 マナ", null, 1);
        this.FREEZE = super.createItemStack(Material.ICE, ChatColor.YELLOW + "フリーズ /5 マナ", null, 1);
        this.EXPLOSION = super.createItemStack(Material.TNT, ChatColor.YELLOW + "エクスプロージョン /10 マナ", null, 1);
        this.ACID_RAIN = super.createItemStack(Material.WATER_BUCKET, ChatColor.YELLOW + "アシッドレイン /7 マナ", null, 1);
        this.ICELC_DROP = super.createItemStack(Material.PACKED_ICE, ChatColor.YELLOW + "アイゼルクドロップ /10 マナ", null, 1);

        this.magicSet = new ItemStack[]{FIRE, FREEZE, EXPLOSION, ACID_RAIN, ICELC_DROP};
        this.magicSets = Arrays.asList(magicSet);

        this.slotSource = slotSource;
        this.guiSource = inventory;

        super.createInventory(this, 9 * 6, "魔法を選択");
    }

    @Override
    public void openInventory(Player player) {
        for(int i = 0; i < magicSets.size(); i++) {
            super.setInventory(magicSets.get(i), i);
        }
        super.openInventory(player);
    }

    /**
     * return magic icons set as Arrays.
     */
    public ItemStack[] getMagicSet() {
        return this.magicSet;
    }

    /**
     * return magic icons set as collection.
     */
    public List<ItemStack> getMagicSets() {
        return this.magicSets;
    }

    public Inventory getGuiSource() {
        return this.guiSource;
    }

    public int getSlotSource() {
        return this.slotSource;
    }

    public boolean checkMagicAmount() {
        Inventory guiSource = this.guiSource;
        int amount = 0;

        for (int i = 0; i < 45; i++) {
            for (ItemStack magicIcon : magicSets) {
                if (magicIcon.equals(guiSource.getItem(i))) {
                    amount++;
                }
            }
        }
        return amount == 3;
    }
}