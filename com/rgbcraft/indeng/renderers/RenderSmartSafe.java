package com.rgbcraft.indeng.renderers;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.indeng.blocks.Blocks;
import com.rgbcraft.indeng.models.ModelSmartSafe;
import com.rgbcraft.indeng.tiles.TileSmartSafe;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderSmartSafe extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	private static int renderId;
	private ModelSmartSafe modelSmartSafe;
	private TileSmartSafe tileSmartSafe;
	
	public RenderSmartSafe() {
		renderId = RenderingRegistry.getNextAvailableRenderId();

		modelSmartSafe = new ModelSmartSafe();
		tileSmartSafe = new TileSmartSafe("0000");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
		int direction;
		if (tileEntity != null) {
			bindTextureByName("/com/rgbcraft/indeng/assets/textures/models/ModelSmartSafe.png");
			
			direction = 2;
			if (tileEntity.getWorldObj().getBlockId(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord) == Blocks.get("blockSmartSafe").blockID) {
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
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);

		GL11.glRotatef(direction * 90, 0.0F, 1.0F, 0.0F);
		
		modelSmartSafe.render((Entity) null, 0.0F, -0.1F, 0.0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        renderTileEntityAt(null, 0.0, 0.0, 0.0, 0.0f);
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
		return renderId;
	}
}
