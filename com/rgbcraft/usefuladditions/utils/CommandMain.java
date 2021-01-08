package com.rgbcraft.usefuladditions.utils;

import java.util.ArrayList;
import java.util.List;

import com.rgbcraft.usefuladditions.UsefulAdditions;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandMain extends CommandBase {

	@Override
	public String getCommandName() {
		return "industrialengineering";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "\2477Usage: \247findustrialengineering <subcommand>";
	}

	@Override
	public List getCommandAliases() {
		List aliases = new ArrayList();
		aliases.add("ie");
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("info")) {
				sender.sendChatToPlayer("\2478\247m+------[\247r \247eIndustrial Engineering\2478 \247m]------+");
        		sender.sendChatToPlayer("");
				sender.sendChatToPlayer("  \2477Version: \247f" + UsefulAdditions.version);
				sender.sendChatToPlayer("  \2477Author: \247balex3025");
				sender.sendChatToPlayer("");
        		sender.sendChatToPlayer("\2478\247m+---------------------------------+");
			}
		}
		
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		List<String> completion = new ArrayList<String>();
		completion.add("info");
        return completion;
    }

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sensder) {
		return true;
	}

}
