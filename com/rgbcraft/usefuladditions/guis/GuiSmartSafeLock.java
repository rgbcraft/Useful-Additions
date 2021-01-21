package com.rgbcraft.usefuladditions.guis;

import org.lwjgl.opengl.GL11;

import com.google.common.io.ByteArrayDataOutput;
import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.containers.ContainerBase;
import com.rgbcraft.usefuladditions.guis.components.GuiTooltip;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;


@SideOnly(Side.CLIENT)
public class GuiSmartSafeLock extends GuiContainer {

	private TileSmartSafe tileSmartSafe;
	private String originalPin = "";
	private GuiTextField pinInput;
	private EntityPlayer player;
	private GuiTooltip tooltip;
	private boolean pinWrong = false;
	
	private int main_width;
	private int main_height;
	private int center;
	private int left;
	private int top;

	public GuiSmartSafeLock(InventoryPlayer inventory, TileSmartSafe tileSmartSafe, EntityPlayer player) {
		super(new ContainerBase(tileSmartSafe));
		
		this.tooltip = new GuiTooltip();
		
		this.player = player;
		this.tileSmartSafe = tileSmartSafe;
		
		xSize = 129;
		ySize = 172;
		
		int BORDER = 4;
        
        this.main_width = this.xSize - BORDER * 2;
        this.main_height = this.ySize - BORDER * 2 - 92;
        
        this.left = BORDER;
        this.top = BORDER;
        this.center = this.left + this.main_width / 2;
	}
	
	private void sendString(int packetId, String value) {
		ByteArrayDataOutput data = NetworkHandler.createBasePacket(packetId, this.tileSmartSafe.xCoord, this.tileSmartSafe.yCoord, this.tileSmartSafe.zCoord);
    	data.writeUTF(value);
		NetworkHandler.sendDataPacketToServer(data);
	}
	
	private void openGui() {
		ByteArrayDataOutput data = NetworkHandler.createBasePacket(10, this.tileSmartSafe.xCoord, this.tileSmartSafe.yCoord, this.tileSmartSafe.zCoord);
		NetworkHandler.sendDataPacketToServer(data);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getTexture(Utils.getResource(ResourceType.GUI, "GuiSmartSafeLock.png")));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if (this.tileSmartSafe.isConfigured())
			Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.smartSafe.lock.set"), this.center, this.top + 5, 0x404040);
		else
			Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.smartSafe.lock.notSet"), this.center, this.top + 5, 0x404040);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.pinInput = new GuiTextField(fontRenderer, guiLeft + 28, guiTop + 25, 72, 20);
		this.pinInput.setMaxStringLength(4);
		
		this.controlList.clear();
		
		int buttonID = 0;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				if (buttonID == 9)
					this.controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, "\2474✘"));
				else if (buttonID == 10)
					this.controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, "0"));
				else if (buttonID == 11)
					this.controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, "\2472✔"));
				else
					this.controlList.add(new GuiButton(buttonID, (guiLeft + 30) + (24 * x), (guiTop + 50) + (24 * y) + 4, 20, 20, String.valueOf(buttonID + 1)));

				buttonID++;
			}
		}
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);
        
        this.pinInput.drawTextBox();
        
		if (isPointInRegion(28, 25, 72, 20, mouseX, mouseY)) {
			this.tooltip.lines.clear();
			this.tooltip.lines.add(LanguageManager.getTranslation("container.smartSafe.lock.toolTip"));
			this.tooltip.draw(mouseX, mouseY);
		}
    }
	
	@Override
	public void updateScreen() {
		super.updateScreen();

		if (this.isShiftKeyDown())
			this.pinInput.setText(this.originalPin);
		else
			if (this.originalPin.length() > 0)
				this.pinInput.setText(new String(new char[this.originalPin.length()]).replace("\0", "*"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 9) {
			if (this.originalPin.length() > 0)
				if (this.pinWrong && this.tileSmartSafe.isConfigured()) {
					this.pinWrong = false;
					this.pinInput.setTextColor(14737632);

					this.originalPin = "";
				} else {
					this.originalPin = this.originalPin.substring(0, this.originalPin.length() - 1);
				}
		} else if (button.id == 11) {
			if (this.isCtrlKeyDown() && this.tileSmartSafe.isConfigured() && !this.originalPin.equals(this.tileSmartSafe.getPin())) {
				if (!(this.pinInput.getText().length() < 4)) {
					if (this.tileSmartSafe.isOwner(this.player) || Utils.isOperator(this.player)) {
						this.sendString(11, this.originalPin);
						
						UsefulAdditions.proxy.sendMessageToPlayer(this.player, LanguageManager.getTranslation("misc.smartSafe.lock.pinUpdated"));
						
						this.pinWrong = false;
						this.pinInput.setTextColor(0x32FC00);

						this.openGui();
					} else {
						UsefulAdditions.proxy.sendMessageToPlayer(this.player, LanguageManager.getTranslation("misc.smartSafe.lock.cannotUpdatePin"));
					}
				} else {
					this.pinWrong = true;
					this.pinInput.setTextColor(0xFC1D00);
				}
				return;
			}
			
			if (this.tileSmartSafe.getPin().equals(this.originalPin) && this.originalPin.length() > 0) {
				this.pinWrong = false;
				this.pinInput.setTextColor(0x32FC00);

				this.openGui();
			} else {
				if (this.originalPin.length() > 0) {
					if (this.tileSmartSafe.isConfigured()) {
						this.pinWrong = true;
					} else {
						if (this.originalPin.length() < 4) {
							this.pinWrong = true;
						} else {
							this.sendString(12, this.player.username);
						
							UsefulAdditions.proxy.sendMessageToPlayer(this.player, LanguageManager.getTranslation("misc.smartSafe.lock.pinCreated"));
						
							this.sendString(11, this.originalPin);
							this.openGui();
						}
					}
					
					if (this.pinWrong)
						this.pinInput.setTextColor(0xFC1D00);
				}
			}
		} else {
			if (this.pinWrong) {
				this.pinWrong = false;
				this.pinInput.setTextColor(14737632);

				this.originalPin = button.displayString;
			} else {
				if (this.originalPin.length() < 4)
					this.originalPin = this.originalPin + button.displayString;
			}
		}
		this.pinInput.setText(this.originalPin);
	}

}
