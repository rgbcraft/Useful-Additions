package com.rgbcraft.indeng.items;

import java.util.List;

import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.blocks.Blocks;
import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.Sounds;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ItemCodebreaker extends Item implements IElectricItem {

	public ItemCodebreaker(int id) {
		super(id);
		
		setItemName("itemCodebreaker");
		setIconIndex(0);
		setMaxStackSize(1);
		setMaxDamage(10);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlockId(x, y,  z) == Blocks.get("smartSafe").blockID && this.canTakeDamage(itemStack, 10)) {
			if (!world.isRemote) {
				TileSmartSafe tile = (TileSmartSafe) world.getBlockTileEntity(x, y, z);
				MinecraftServer server = MinecraftServer.getServer();

				if (!(tile.getOwner().equalsIgnoreCase(player.username) || player.username.equalsIgnoreCase(server.getServerOwner()) || server.getConfigurationManager().areCommandsAllowed(player.username))) {
					player.sendChatToPlayer("§cNon puoi hackerare una safe altrui!");
					return false;
				}

				FMLNetworkHandler.openGui(player, IndustrialEngineering.instance, 1, world, x, y, z);
				player.sendChatToPlayer("§aHai hackerato la Smart Safe di §f" + tile.getOwner() + "§a.");
				this.damage(itemStack, 10, player);
				return true;
			} else {
				Sounds.CodeBreaker.play(x, y, z, 1, 1);
			}
		}
		return false;
	}
	
	private boolean canTakeDamage(ItemStack stack, int amount) {
		amount *= 50;
        return ElectricItem.discharge(stack, amount, Integer.MAX_VALUE, true, true) == amount;
    }
	
	private void damage(final ItemStack is, final int damage, final EntityPlayer player) {
		 ElectricItem.use(is, 50 * damage, player);
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
        return 5000;
    }
    
    @Override
    public int getTier() {
        return 1;
    }
    
    @Override
    public int getTransferLimit() {
        return 250;
    }
    
    public void getSubItems(final int i, final CreativeTabs tabs, final List itemList) {
        final ItemStack charged = new ItemStack((Item) this, 1);
        ElectricItem.charge(charged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        itemList.add(charged);
        itemList.add(new ItemStack((Item) this, 1, this.getMaxDamage()));
    }
    
    
    public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
        return false;
    }

}
