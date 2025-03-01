package dev.axeman.jedis.commands;

import dev.axeman.jedis.command.CommandContext;
import dev.axeman.jedis.command.ICommand;
import dev.axeman.jedis.server.Encode;
import dev.axeman.jedis.server.Message;

public class EchoCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		if(ctx.getArgs().size() == 1)
			ctx.getReply().reply(String.join(" ", ctx.getArgs()), Encode.BULK_STRING);
		else
			ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
	}

	@Override
	public String getCommand() {
		return "echo";
	}

	@Override
	public String getHelp() {
		return "Echos Message";
	}
}
