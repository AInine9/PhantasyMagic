package hugu1026.com.github.phantasymagic.gui;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MagicSelectGui extends Gui {

    private ItemStack FIRE, FREEZE, EXPLOSION, ACID_RAIN, ICELC_DROP, THUNDER_BOLT, HEAL;
    private List<String> FIRElore = new ArrayList<>(),
            FREEZElore = new ArrayList<>(),
            EXPLOSIONlore = new ArrayList<>(),
            ACID_RAINlore = new ArrayList<>(),
            ICELC_DROPlore = new ArrayList<>(),
            THUNDER_BOLTlore = new ArrayList<>(),
            HEALlore = new ArrayList<>();
    private ItemStack[] magicSet;
    private List<ItemStack> magicSets;
    private Inventory guiSource;
    private int slotSource;
    private HashMap<String, ItemStack> magicListName = new HashMap<>();

    public MagicSelectGui(Inventory inventory, int slotSource) {
        this.createLore();

        this.FIRE = super.createItemStack(Material.FIREBALL, ChatColor.YELLOW + "ファイヤ /5 マナ /3 スピリット", FIRElore, 1);
        this.FREEZE = super.createItemStack(Material.ICE, ChatColor.YELLOW + "フリーズ /5 マナ /3 スピリット", FREEZElore, 1);
        this.EXPLOSION = super.createItemStack(Material.TNT, ChatColor.YELLOW + "エクスプロージョン /10 マナ /10 スピリット", EXPLOSIONlore, 1);
        this.ACID_RAIN = super.createItemStack(Material.WATER_BUCKET, ChatColor.YELLOW + "アシッドレイン /7 マナ /8 スピリット", ACID_RAINlore, 1);
        this.ICELC_DROP = super.createItemStack(Material.PACKED_ICE, ChatColor.YELLOW + "アイゼルクドロップ /10 マナ /10 スピリット", ICELC_DROPlore, 1);
        this.THUNDER_BOLT = super.createItemStack(Material.SPECTRAL_ARROW, ChatColor.YELLOW + "サンダーボルト /10 マナ /9 スピリット", THUNDER_BOLTlore, 1);
        this.HEAL = super.createItemStack(Material.POTION, ChatColor.YELLOW + "ヒール /7 マナ /5 スピリット", HEALlore, 1);

        this.magicSet = new ItemStack[]{FIRE, FREEZE, EXPLOSION, ACID_RAIN, ICELC_DROP, THUNDER_BOLT, HEAL};
        this.magicSets = Arrays.asList(magicSet);

        this.slotSource = slotSource;
        this.guiSource = inventory;

        magicListName.put("fire", FIRE);
        magicListName.put("freeze", FREEZE);
        magicListName.put("explosion", EXPLOSION);
        magicListName.put("acid rain", ACID_RAIN);
        magicListName.put("icelc drop", ICELC_DROP);
        magicListName.put("thunder bolt", THUNDER_BOLT);
        magicListName.put("heal", HEAL);

        super.createInventory(this, 9 * 6, "魔法を選択");
    }

    public void createLore() {
        this.FIRElore.add(0, ChatColor.RED + "敵を炎上させる");
        this.FREEZElore.add(0, ChatColor.RED + "敵を凍り付け、移動不可能にする");
        this.EXPLOSIONlore.add(0, ChatColor.RED + "大爆発を引き起こす");
        this.ACID_RAINlore.add(0, ChatColor.RED + "酸性の雨を降らす");
        this.ICELC_DROPlore.add(0, ChatColor.RED + "雷撃を纏った氷塊を落下させる");
        this.THUNDER_BOLTlore.add(0, ChatColor.RED + "稲妻を落とす");
        this.HEALlore.add(0, ChatColor.RED + "対象のプレイヤーの体力を回復する");
    }

    @Override
    public void openInventory(Player player) {
        List<String> magicList = PlayerDataUtil.getMagicList(player);
        for (String name : magicList) {
            addInventory(magicListName.get(name));
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
        MagicSquareGui magicSquareGui = (MagicSquareGui) guiSource.getHolder();
        int amount = 0;
        int playerMaxMagicAmount = PlayerDataUtil.getMagicAmountLimit(player);

        for (int i = 0; i < 45; i++) {
            ItemStack magicIcon = guiSource.getItem(i);
            if (!magicSquareGui.checkItemisIcon(magicIcon)) {
                amount++;
            }
        }
        return amount == playerMaxMagicAmount;
    }
}