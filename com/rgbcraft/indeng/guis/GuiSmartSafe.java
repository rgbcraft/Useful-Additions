package com.rgbcraft.indeng.guis;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.indeng.containers.ContainerSmartSafe;
import com.rgbcraft.indeng.tiles.TileSmartSafe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.packet.Packet250CustomPayload;

@SideOnly(Side.CLIENT)
public class GuiSmartSafe extends GuiContainer {

	private TileSmartSafe tileSmartSafe;
	private String originalPin = "";
	private GuiTextField pinInput;
	private EntityPlayer player;
	private boolean pinWrong = false;
	private boolean initialized;

	public GuiSmartSafe(InventoryPlayer inventory, TileSmartSafe tileSmartSafe, EntityPlayer player) {
		super(new ContainerSmartSafe(inventory, tileSmartSafe));
		
		this.tileSmartSafe = tileSmartSafe;
		this.player = player;
		this.initialized = this.tileSmartSafe.getPin().equals("0000");
		
		xSize = 129;
		ySize = 172;
	}

	private static final int texture = Minecraft.getMinecraft().renderEngine.getTexture("/com/rgbcraft/indeng/assets/guis/SmartSafeLock.png");
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		if (this.initialized) {
			fontRenderer.drawString("Crea un PIN:", 33, 9, 0x404040);
		} else {
			fontRenderer.drawString("Inserisci il PIN:", 27, 10, 0x404040);
		}
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.pinInput = new GuiTextField(fontRenderer, guiLeft + 28, guiTop + 25, 72, 20);
		this.pinInput.setMaxStringLength(4);
		
		controlList.clear();
		
		int buttonID = 0;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				if (buttonID == 9) {
					controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, "✘"));
				} else if (buttonID == 10) {
					controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, "0"));
				} else if (buttonID == 11) {
					controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, "✔"));
				} else {
					controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, String.valueOf(buttonID + 1)));
				}

				buttonID++;
			}
		}
	}

	@Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        this.pinInput.drawTextBox();
    }
	
	@Override
	public void updateScreen() {
		super.updateScreen();

		if (super.isShiftKeyDown()) {
			this.pinInput.setText(this.originalPin);
		} else {
			if (this.originalPin.length() > 0) {
				this.pinInput.setText(new String(new char[this.originalPin.length()]).replace("\0", "*"));
			}
		}
	}
	
	@Override
	protected void keyTyped(char key, int id) {
		if (id >= 2 && id <= 11) {
			this.pinInput.textboxKeyTyped(key, id);
		} else if (id >= 71 && id <= 82) {
			this.pinInput.textboxKeyTyped(key, id);
		} else {
			super.keyTyped(key, id);
		}
    }
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 9) {
			if (this.originalPin.length() > 0) {
				if (pinWrong && !this.initialized) {
					this.pinWrong = false;
					this.pinInput.setTextColor(14737632);

					this.originalPin = "";
				} else {
					this.originalPin = this.originalPin.substring(0, this.originalPin.length() - 1);
				}
			}
		} else if (button.id == 11) {
			if (this.tileSmartSafe.getPin().equals(this.originalPin)) {
				this.pinWrong = false;
				this.pinInput.setTextColor(0x32FC00);
				this.player.displayGUIChest((TileSmartSafe) this.tileSmartSafe.getInventory());
			} else {
				if (this.originalPin.length() > 0) {
					if (!this.initialized) {
						this.pinWrong = true;
					} else {
						if (this.originalPin.length() < 4) {
							this.pinWrong = true;
						} else {
							this.tileSmartSafe.setPin(this.originalPin);
							this.player.sendChatToPlayer("§aPIN creato correttamente!");
							this.player.closeScreen();
						}
					}
					
					if (pinWrong) {
						this.pinInput.setTextColor(0xFC1D00);
					}
				}
			}
		} else {
			if (pinWrong) {
				this.pinWrong = false;
				this.pinInput.setTextColor(14737632);

				this.originalPin = button.displayString;
			} else {
				this.originalPin = this.originalPin + button.displayString;
			}
		}
		this.pinInput.setText(this.originalPin);
	}
}
