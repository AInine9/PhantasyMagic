package hugu1026.com.github.phantasymagic.gui;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicSelectGui extends Gui {

    private ItemStack FIRE, FREEZE, EXPLOSION, ACID_RAIN, ICELC_DROP;
    private List<String> FIRElore = new ArrayList<>(),
            FREEZElore = new ArrayList<>(),
            EXPLOSIONlore = new ArrayList<>(),
            ACID_RAINlore = new ArrayList<>(),
            ICELC_DROPlore = new ArrayList<>();
    private ItemStack[] magicSet;
    private List<ItemStack> magicSets;
    private Inventory guiSource;
    private int slotSource;

    public MagicSelectGui(Inventory inventory, int slotSource) {
        this.createLore();

        this.FIRE = super.createItemStack(Material.FIREBALL, ChatColor.YELLOW + "ファイヤ /5 マナ /3 スピリット", FIRElore, 1);
        this.FREEZE = super.createItemStack(Material.ICE, ChatColor.YELLOW + "フリーズ /5 マナ /3 スピリット", FREEZElore, 1);
        this.EXPLOSION = super.createItemStack(Material.TNT, ChatColor.YELLOW + "エクスプロージョン /10 マナ /10 スピリット", EXPLOSIONlore, 1);
        this.ACID_RAIN = super.createItemStack(Material.WATER_BUCKET, ChatColor.YELLOW + "アシッドレイン /7 マナ /8 スピリット", ACID_RAINlore, 1);
        this.ICELC_DROP = super.createItemStack(Material.PACKED_ICE, ChatColor.YELLOW + "アイゼルクドロップ /10 マナ /10 スピリット", ICELC_DROPlore, 1);

        this.magicSet = new ItemStack[]{FIRE, FREEZE, EXPLOSION, ACID_RAIN, ICELC_DROP};
        this.magicSets = Arrays.asList(magicSet);

        this.slotSource = slotSource;
        this.guiSource = inventory;

        super.createInventory(this, 9 * 6, "魔法を選択");
    }

    @Override
    public void openInventory(Player player) {
        for (int i = 0; i < magicSets.size(); i++) {
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

    public boolean checkMagicAmount(Player player) {
        Inventory guiSource = this.guiSource;
        int amount = 0;
        int playerMaxMagicAmount = PlayerDataUtil.getMagicAmountLimit(player);

        for (int i = 0; i < 45; i++) {
            for (ItemStack magicIcon : magicSets) {
                if (magicIcon.equals(guiSource.getItem(i))) {
                    amount++;
                }
            }
        }

        return amount == playerMaxMagicAmount;
    }

    public void createLore(){
        this.FIRElore.add(0, ChatColor.RED + "敵を炎上させる");
        this.FREEZElore.add(0, ChatColor.RED + "敵を凍り付け、移動不可能にする");
        this.EXPLOSIONlore.add(0, ChatColor.RED + "大爆発を引き起こす");
        this.ACID_RAINlore.add(0, ChatColor.RED + "酸性の雨を降らす");
        this.ICELC_DROPlore.add(0, ChatColor.RED + "雷撃を纏った氷塊を落下させる");
    }
}