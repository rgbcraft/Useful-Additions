package com.rgbcraft.usefuladditions.containers;

import java.util.ArrayList;

import com.rgbcraft.usefuladditions.guis.slots.SlotSpecific;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerOsmosisGenerator extends ContainerBase {

    public TileOsmosisGenerator tileOsmosisGenerator;
    private int lastTankAmount = 0;

    public ContainerOsmosisGenerator(InventoryPlayer inventory, TileOsmosisGenerator tileOsmosisGenerator) {
        super(tileOsmosisGenerator);

        this.tileOsmosisGenerator = tileOsmosisGenerator;

        this.addSlotToContainer(new SlotSpecific(tileOsmosisGenerator, 0, 98, 20, new ArrayList<ItemStack>() {

            {
                this.add(new ItemStack(Items.get("canister"), 1, 5));
            }

        }));
        this.addSlotToContainer(new SlotSpecific(tileOsmosisGenerator, 1, 98, 51, new ArrayList<ItemStack>()));

        this.bindPlayerInventory(inventory);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.length;

            if (index < containerSlots) {
                if (itemstack.isItemEqual(new ItemStack(Items.get("canister"), 1, 5)) || itemstack.isItemEqual(new ItemStack(Items.get("canister"), 1, 0)))
                    if (!this.mergeItemStack(itemstack1, containerSlots, this.inventorySlots.size(), true))
                        return null;
            } else if (itemstack.isItemEqual(new ItemStack(Items.get("canister"), 1, 5)) && !this.mergeItemStack(itemstack1, 0, containerSlots, false))
                return null;

            if (itemstack1.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if (itemstack1.stackSize == itemstack.stackSize)
                return null;

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileOsmosisGenerator.isUseableByPlayer(entityPlayer);
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);

        this.tileOsmosisGenerator.sendGUIUpdateData(this, crafter);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        int tankAmount = this.tileOsmosisGenerator.getTankAmount();

        for (Object element : this.crafters) {
            ICrafting crafter = (ICrafting) element;
            if (this.lastTankAmount != tankAmount)
                this.tileOsmosisGenerator.sendGUIUpdateData(this, crafter);
        }

        this.lastTankAmount = tankAmount;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int key, int value) {
        super.updateProgressBar(key, value);

        this.tileOsmosisGenerator.getGUIUpdateData(key, value);
    }

}
