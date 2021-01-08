package com.rgbcraft.usefuladditions.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rgbcraft.usefuladditions.utils.ICardInfoProvider;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import shedar.mods.ic2.nuclearcontrol.api.CardState;
import shedar.mods.ic2.nuclearcontrol.api.ICardWrapper;
import shedar.mods.ic2.nuclearcontrol.api.IPanelDataSource;
import shedar.mods.ic2.nuclearcontrol.api.IRemoteSensor;
import shedar.mods.ic2.nuclearcontrol.api.PanelSetting;
import shedar.mods.ic2.nuclearcontrol.api.PanelString;
import shedar.mods.ic2.nuclearcontrol.panel.CardWrapperImpl;

public class ItemIESensorCard extends Item implements IRemoteSensor, IPanelDataSource {
	
    public static final UUID unique_id = new UUID(0, 41);
    
	public ItemIESensorCard(int id) {
		super(id);
		
		setItemName("IESensorCard");
		setMaxStackSize(1);
		setIconIndex(47);
		setTextureFile(Items.itemsTextureFile);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
    	if (!world.isRemote && entityPlayer.isSneaking()) {
    		return new ItemStack(Items.get("IESensorKit"));
    	}
        return itemStack;
    }
    
	@Override
	public CardState update(TileEntity panel, ICardWrapper card, int maxRange) {
        ChunkCoordinates target = card.getTarget();
        TileEntity te = panel.worldObj.getBlockTileEntity(target.posX, target.posY, target.posZ);
        if (te != null && te instanceof ICardInfoProvider) {
        	ICardInfoProvider provider = (ICardInfoProvider) te;
        	
        	Map<String, String> rows = provider.getRows(new HashMap<String, String>(3));
        	int index = 1;
        	for (Map.Entry<String, String> row : rows.entrySet()) {
        		card.setString("row" + String.valueOf(index), String.format("%s:&&%s", row.getKey(), row.getValue()));
        		index++;
        	}

            return CardState.OK;
        } else {
            return CardState.NO_TARGET;
        }
	}

	@Override
	public List<PanelString> getStringData(int displaySettings, ICardWrapper card, boolean showLabels) {
        List<PanelString> result = new LinkedList<PanelString>();
        
        String firstRow = card.getString("row1");
        if (firstRow != null && !firstRow.equals(":&&")) {
        	if ((displaySettings & 1) != 0)  {
                PanelString line = new PanelString();
 
                String value = "";
                if (firstRow.split("&&").length > 1)
                	value = firstRow.split("&&")[1];
                
                if (showLabels)
                	line.textLeft = firstRow.split("&&")[0] + " " + value;
                else
                	line.textLeft = value;

                result.add(line);
            }
        }

        String secondRow = card.getString("row2");
        if (secondRow != null && !secondRow.equals(":&&")) {
        	if ((displaySettings & 2) != 0)  {
                PanelString line = new PanelString();
                
                String value = "";
		        if (secondRow.split("&&").length > 1)
		        	value = secondRow.split("&&")[1];
		        
		        if (showLabels)
		        	line.textLeft = secondRow.split("&&")[0] + " " + value;
		        else
		        	line.textLeft = value;

                result.add(line);
            }
        }

        String thirdRow = card.getString("row3");
        if (thirdRow != null && !thirdRow.equals(":&&")) {
        	if ((displaySettings & 4) != 0)  {
                PanelString line = new PanelString();
                
                String value = "";
		        if (thirdRow.split("&&").length > 1)
		        	value = thirdRow.split("&&")[1];
		        
		        if (showLabels)
		        	line.textLeft = thirdRow.split("&&")[0] + " " + value;
		        else
		        	line.textLeft = value;

                result.add(line);
            }
        }

        return result;
	}

	@Override
    public List<PanelSetting> getSettingsList() {
        List<PanelSetting> result = new ArrayList<PanelSetting>();
        result.add(new PanelSetting("First Row", 1, this.unique_id));
        result.add(new PanelSetting("Second Row", 2, this.unique_id));
        result.add(new PanelSetting("Third Row", 4, this.unique_id));
        return result;
    }

	@Override
	public UUID getCardType() {
		return this.unique_id;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List toolTip, boolean advanced) {
        CardWrapperImpl helper = new CardWrapperImpl(itemStack);
        ChunkCoordinates target = helper.getTarget();
      
        if (target != null) {
        	TileEntity te = entityPlayer.worldObj.getBlockTileEntity(target.posX, target.posY, target.posZ);
        	if (te != null && te instanceof ICardInfoProvider) {
        		ICardInfoProvider provider = (ICardInfoProvider) te;
        		
        		toolTip.add("\2479" + provider.getName());
        		
        		String title = helper.getTitle();
                if(title != null && !title.isEmpty()) {
                    toolTip.add(title);
                }

                toolTip.add(String.format("X: %d, Y: %d, Z: %d", target.posX, target.posY, target.posZ));
        	}
        }
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.uncommon;
    }
	
}
