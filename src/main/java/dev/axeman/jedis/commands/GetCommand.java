package dev.axeman.jedis.commands;

import dev.axeman.jedis.command.CommandContext;
import dev.axeman.jedis.command.ICommand;
import dev.axeman.jedis.core.DatabaseManager;
import dev.axeman.jedis.server.Encode;
import dev.axeman.jedis.server.Message;
import dev.axeman.jedis.server.RESP;

public class GetCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		if (ctx.getArgs().size() != 1) {
			ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
			return;
		}

		String key = ctx.getArgs().get(0);
		String value = DatabaseManager.get(key);
		if(value == null) {
			ctx.getReply().reply(RESP.nullBulkString(), Encode.NONE);
			return;
		}

		ctx.getReply().reply(value, Encode.BULK_STRING);
	}

	@Override
	public String getCommand() {
		return "get";
	}

	@Override
	public String getHelp() {
		return "get key value";
	}
}
