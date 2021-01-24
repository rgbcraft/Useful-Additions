package com.rgbcraft.usefuladditions.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.api.IDebuggable;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;

import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemDebugger extends ItemBase implements IElectricItem {

    public ItemDebugger(int id) {
        super(id);

        setItemName("debugger");
        setMaxStackSize(1);
        setMaxDamage(27);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && !entityPlayer.isSneaking()) {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(itemStack);
            boolean isAdvanced = nbtData.getBoolean("advanced");

            if (this.canTakeDamage(itemStack, isAdvanced ? 10 : 5)) {
                List<String> advancedContent = new ArrayList<String>();
                List<String> debugContent = new ArrayList<String>();

                if (isAdvanced) {
                    advancedContent.add("  " + LanguageManager.getFormattedTranslation("item.debugger.advanced.id", world.getBlockId(x, y, z)));
                    advancedContent.add("  " + LanguageManager.getFormattedTranslation("item.debugger.advanced.metadata", world.getBlockMetadata(x, y, z)));
                }

                TileEntity te = world.getBlockTileEntity(x, y, z);
                if (te instanceof IDebuggable) {
                    IDebuggable debugged = (IDebuggable) te;

                    if (isAdvanced) {
                        Map<String, String> additionalInfos = debugged.getAdditionalAdvancedInfos(entityPlayer, new HashMap<String, String>());
                        if (additionalInfos != null)
                            for (Map.Entry<String, String> additionalInfo : additionalInfos.entrySet())
                                advancedContent.add("  \247e" + additionalInfo.getKey() + " \2477" + additionalInfo.getValue());
                    }

                    Map<String, Boolean> requirements = debugged.getRequirements(entityPlayer, new HashMap<String, Boolean>());
                    if (requirements != null)
                        if (!requirements.isEmpty())
                            for (Map.Entry<String, Boolean> requirement : requirements.entrySet())
                                debugContent.add("  " + (requirement.getValue() ? "\2478[\247r \2472✔ \2478] >\2477 " : "\2478[\247r \2474✘ \2478] >\2477 ") + requirement.getKey());
                }

                if (advancedContent.size() > 0 || debugContent.size() > 0) {
                    UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, LanguageManager.getTranslation("item.debugger.header"));
                    UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, "");

                    if (advancedContent.size() > 0) {
                        for (String text : advancedContent)
                            UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, text);
                        UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, "");
                    }

                    if (debugContent.size() > 0) {
                        for (String text : debugContent)
                            UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, text);
                        UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, "");
                    }

                    UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, LanguageManager.getTranslation("item.debugger.footer"));

                    if (!entityPlayer.capabilities.isCreativeMode)
                        this.damage(itemStack, isAdvanced ? 10 : 5, entityPlayer);
                }
            }
        }
        return false;
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote && entityPlayer.isSneaking()) {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(itemStack);
            if (nbtData.getBoolean("advanced")) {
                nbtData.setBoolean("advanced", false);
                UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, LanguageManager.getTranslation("item.debugger.advanced.disabled"));
            } else {
                nbtData.setBoolean("advanced", true);
                UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, LanguageManager.getTranslation("item.debugger.advanced.enabled"));
            }
        }
        return itemStack;
    }

    @Override
    public int getIconFromDamage(int damage) {
        if (damage >= 26)
            return 2;
        return 1;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List toolTip, boolean par4) {
        NBTTagCompound nbtData = Utils.getOrCreateNbtData(itemStack);
        toolTip.add((nbtData.getBoolean("advanced") ? LanguageManager.getTranslation("item.debugger.desc.line1.enabled") : LanguageManager.getTranslation("item.debugger.desc.line1.disabled")));

        if (GuiScreen.isShiftKeyDown())
            toolTip.add(LanguageManager.getTranslation("item.debugger.desc.line2"));
    }

    private boolean canTakeDamage(ItemStack stack, int amount) {
        amount *= 50;
        return ElectricItem.discharge(stack, amount, Integer.MAX_VALUE, true, true) == amount;
    }

    private void damage(ItemStack itemStack, int amount, EntityPlayer player) {
        ElectricItem.use(itemStack, 50 * amount, player);
    }

    public static boolean isAdvancedModeActived(ItemStack itemStack) {
        return Utils.getOrCreateNbtData(itemStack).getBoolean("advanced");
    }

    @Override
    public boolean canProvideEnergy() {
        return false;
    }

    @Override
    public int getChargedItemId() {
        return super.itemID;
    }

    @Override
    public int getEmptyItemId() {
        return super.itemID;
    }

    @Override
    public int getMaxCharge() {
        return 12000;
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getTransferLimit() {
        return 250;
    }

    @Override
    public void getSubItems(int id, CreativeTabs tabs, List itemList) {
        final ItemStack charged = new ItemStack(this, 1);
        ElectricItem.charge(charged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        itemList.add(charged);
        itemList.add(new ItemStack(this, 1, this.getMaxDamage()));
    }

    @Override
    public boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2) {
        return false;
    }

}
