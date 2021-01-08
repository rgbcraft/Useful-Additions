package com.rgbcraft.usefuladditions.items;

import java.util.List;

import com.rgbcraft.usefuladditions.utils.CreativeTab;
import com.rgbcraft.usefuladditions.utils.Utils;

import buildcraft.api.core.Position;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemAdjustableBlockPlacer extends ItemBase implements IElectricItem {

	public ItemAdjustableBlockPlacer(int id) {
		super(id);

		setItemName("adjustableBlockPlacer");
		setMaxStackSize(1);
		setMaxDamage(27);
	}
	
	private int availableBlocks(EntityPlayer entityPlayer, int maxBlocks, int id, int metadata) {
		int amount = 0;
		for (int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++) {
			ItemStack item = entityPlayer.inventory.getStackInSlot(i);
			if (item != null) {
				if (item.isItemEqual(new ItemStack(id, 1, metadata))) {
					if (entityPlayer.capabilities.isCreativeMode)
						return maxBlocks;
					
					amount += item.stackSize;
				}
				
				if (amount >= maxBlocks) {
					return maxBlocks;
				}
			}
		}
		return amount;
	}
	
	private void removeBlockFromInventory(EntityPlayer entityPlayer, int id, int metadata) {
		if (!entityPlayer.capabilities.isCreativeMode) {
			for (int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++) {
				ItemStack item = entityPlayer.inventory.getStackInSlot(i);
				if (item != null) {
					if (item.isItemEqual(new ItemStack(id, 1, metadata))) {
						if (item.stackSize > 0)
							item.stackSize--;
						else
							item = null;

						entityPlayer.inventoryContainer.detectAndSendChanges();
						break;						
					}
				}
			}
		}
	}
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    	if (!world.isRemote && !entityPlayer.isSneaking()) {
//    		NBTTagCompound nbtData = Utils.getOrCreateNbtData(itemStack);
//    		if (nbtData.getBoolean("advanced")) {
//    			nbtData.setBoolean("advanced", false);
//    			entityPlayer.sendChatToPlayer("\247cModalità avanzata disattivata.");
//    		} else {
//    			nbtData.setBoolean("advanced", true);
//    			entityPlayer.sendChatToPlayer("\247aModalità avanzata attivata.");
//    		}
    		
    		ForgeDirection orientation = Utils.get3dOrientation(new Position(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ), new Position(x, y, z));
    		
    		int selectedBlockId = world.getBlockId(x, y, z);
    		int selectedBlockMetadata = world.getBlockMetadata(x, y, z);
    		
    		for (int i = 0; i < 10; i++) {
    			if (world.isAirBlock(x + i, y + 1, z) && this.availableBlocks(entityPlayer, 10, selectedBlockId, selectedBlockMetadata) > 0) {
    				world.setBlockAndMetadataWithNotify(x + i, y + 1, z, selectedBlockId, selectedBlockMetadata);
    				this.removeBlockFromInventory(entityPlayer, selectedBlockId, selectedBlockMetadata);
    			}
    			
    			if (world.isAirBlock(x - i, y + 1, z)  && this.availableBlocks(entityPlayer, 10, selectedBlockId, selectedBlockMetadata) > 0) {
    				world.setBlockAndMetadataWithNotify(x - i, y + 1, z, selectedBlockId, selectedBlockMetadata);
    				this.removeBlockFromInventory(entityPlayer, selectedBlockId, selectedBlockMetadata);
    			}
    		}
    		return true;
    	}
		return false;
    }
    
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List toolTip, boolean par4) {
//    	NBTTagCompound nbtData = Utils.getOrCreateNbtData(itemStack);
//    	toolTip.add("Modalità avanzata: " + (nbtData.getBoolean("advanced") ? "\247aAttivata" : "\247cDisattivata"));
//    	toolTip.add("\247oSHIFT + Tasto Destro\247r\2477 per cambiare modalità.");
    }
    
    
    @Override
    public int getIconFromDamage(int damage) {
    	if (damage >= 26) {
    		return 3;
    	}
    	return 4;
    }
    

    private boolean canTakeDamage(ItemStack stack, int amount) {
        amount *= 50;
        return ElectricItem.discharge(stack, amount, Integer.MAX_VALUE, true, true) == amount;
    }
    
    private void damage(ItemStack itemStack, int amount, EntityPlayer player) {
        ElectricItem.use(itemStack, 50 * amount, player);
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
        return 25000;
    }
    
    @Override
    public int getTier() {
        return 2;
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
