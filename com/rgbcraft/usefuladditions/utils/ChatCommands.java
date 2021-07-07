package com.rgbcraft.usefuladditions.utils;

import java.util.ArrayList;
import java.util.List;

import com.rgbcraft.usefuladditions.UsefulAdditions;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;


public class ChatCommands extends CommandBase {

    @Override
    public String getCommandName() {
        return "usefuladditions";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/usefuladditions <args>";
    }

    @Override
    public List getCommandAliases() {
        List aliases = new ArrayList();
        aliases.add("ua");
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        sender.sendChatToPlayer("\2478\247m+---------[\247r \247eUseful Additions\2478 \247m]---------+");
        sender.sendChatToPlayer("");

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("ping")) {
                String pingColor = "";
                int ping = ((EntityPlayerMP) sender).ping;

                if (ping < 70)
                    pingColor = "a";
                else if (ping >= 70 && ping <= 100)
                    pingColor = "e";
                else
                    pingColor = "c";

                sender.sendChatToPlayer("  \2477Ping: \247" + pingColor + Integer.toString(ping) + "ms");
            } else
                sender.sendChatToPlayer("\247c  Comando inesistente!");
        } else {
            sender.sendChatToPlayer("  \2477Version: \247f" + UsefulAdditions.version);
            sender.sendChatToPlayer("  \2477Author: \247falex3025");
            sender.sendChatToPlayer("  \2477Credits: \247fItzTacos, lego11, Fabrimat");
        }

        sender.sendChatToPlayer("");
        sender.sendChatToPlayer("\2478\247m+----------------------------------+");
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        List<String> completion = new ArrayList<String>();
        completion.add("ping");
        return completion;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sensder) {
        return true;
    }

}
