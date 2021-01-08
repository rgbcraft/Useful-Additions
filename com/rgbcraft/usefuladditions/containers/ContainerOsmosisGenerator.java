package com.rgbcraft.usefuladditions.containers;

import java.util.ArrayList;

import com.rgbcraft.usefuladditions.gui.slots.SlotSpecific;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerOsmosisGenerator extends Container {
	
	public TileOsmosisGenerator tileOsmosisGenerator;

	public ContainerOsmosisGenerator(InventoryPlayer inventory, TileOsmosisGenerator tileOsmosisGenerator) {
		this.tileOsmosisGenerator = tileOsmosisGenerator;
		
		this.addSlotToContainer(new SlotSpecific((IInventory) tileOsmosisGenerator, 0, 98, 20, new ArrayList<Item>(){{add(Items.get("saltWaterCanister"));}}));
		this.addSlotToContainer(new SlotSpecific((IInventory) tileOsmosisGenerator, 1, 98, 51, new ArrayList<Item>()));
		
		this.bindPlayerInventory((IInventory) inventory);
	}
	
	public void bindPlayerInventory(IInventory inventory) {
        // main 9x3 slot inventory at 8, 84; inventory slots 9-35; container slots 4-30
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventory, 9 + (i * 9) + j, 8 + (j * 18), 84 + (i * 18)));
            }
        }

        // hotbar at 8, 142; inventory slots 0-8; container slots 31-39
        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventory, i, 8 + (i * 18), 142));
        }
    }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {	
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.tileOsmosisGenerator.isUseableByPlayer(entityPlayer);
	}
	
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < crafters.size(); i++) {
            this.tileOsmosisGenerator.sendGUINetworkData(this, (ICrafting) crafters.get(i));
        }
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int key, int value) {
        this.tileOsmosisGenerator.getGUINetworkData(key, value);
    }

}
