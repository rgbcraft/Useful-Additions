package com.rgbcraft.usefuladditions.renderers;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.usefuladditions.models.ModelSmartSafe;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderSmartSafe extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	private static int renderId;
	private ModelSmartSafe modelSmartSafe;
	private ModelSmartSafe modelSmartSafeInventory;
	
	public RenderSmartSafe() {
		this.modelSmartSafe = new ModelSmartSafe();
		this.modelSmartSafeInventory = new ModelSmartSafe();

		this.renderId = RenderingRegistry.getNextAvailableRenderId();
	}
	
	private void renderSafe(TileEntity tileEntity, double x, double y, double z, float partialTickTime, ModelSmartSafe model) {
		int direction;
		if (tileEntity != null) {
			bindTextureByName(Utils.getResource(ResourceType.MODEL, "ModelSmartSafe.png"));
			
			direction = 2;
			if (tileEntity instanceof TileSmartSafe) {
				direction = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord) + 1;
				if (direction == 1) {
					direction = 3;
				} else if (direction == 3) {
					direction = 1;
				} else if (direction == 2) {
					direction = 4;
				} else if (direction == 4) {
					direction = 2;
				}
			}
		} else {
			direction = 3;
		}
		
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);

		GL11.glRotatef(direction * 90, 0.0F, 1.0F, 0.0F);
		
		if (tileEntity != null && tileEntity instanceof TileSmartSafe) {
			TileSmartSafe tile = (TileSmartSafe) tileEntity;
	        float doorAngle = tile.prevDoorAngle + (tile.doorAngle - tile.prevDoorAngle) * partialTickTime;
	        doorAngle = 1.0f - doorAngle;
	        doorAngle = 1.0f - doorAngle * doorAngle * doorAngle;
	        
	        model.Shape6.rotateAngleY = doorAngle * 3.1415927f / 2.0f;

	        if (doorAngle != 0) {
	        	model.Shape7.isHidden = true;
	        	model.Shape8.isHidden = true;
	        } else {
	        	model.Shape7.isHidden = false;
		        model.Shape8.isHidden = false;
	        }
		}

		model.render();
		
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
		this.renderSafe(tileEntity, x, y, z, partialTickTime, this.modelSmartSafe);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        this.renderSafe(null, 0.0, 0.0, 0.0, 0.0f, this.modelSmartSafeInventory);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return this.renderId;
	}
}
