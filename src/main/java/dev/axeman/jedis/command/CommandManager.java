package dev.axeman.jedis.command;

import dev.axeman.jedis.commands.EchoCommand;
import dev.axeman.jedis.commands.GetCommand;
import dev.axeman.jedis.commands.PingCommand;
import dev.axeman.jedis.commands.SetCommand;
import dev.axeman.jedis.server.Encode;
import dev.axeman.jedis.server.Message;
import dev.axeman.jedis.server.Reply;
import dev.axeman.jedis.server.Request;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
	private List<ICommand> commands;

	public CommandManager() {
		commands = new ArrayList<>();

		addCommend(new PingCommand());
		addCommend(new EchoCommand());
		addCommend(new GetCommand());
		addCommend(new SetCommand());
	}

	public CommandManager(List<ICommand> commands) {
		this.commands = commands;
	}

	public void addCommend(ICommand command) {
		if(!hasCommand(command))
			commands.add(command);
	}

	public void handle(Request request, Reply reply) {
		if(request.getCmd() != null) {
			ICommand command = getCommand(request.getCmd().toLowerCase());
			if(command != null) {
				command.execute(new CommandContext(request.getCmd(), request.getArgs(), reply));
			} else {
				reply.reply(Message.unknownCommand(request.getCmd(), request.getArgs()), Encode.ERROR);
			}
		}
	}

	private boolean hasCommand(ICommand command) {
		return getCommand(command.getCommand()) != null;
	}

	private ICommand getCommand(String command) {
		for(ICommand cmd: commands)
			if(cmd.getCommand().equals(command))
				return cmd;
		return null;
	}
}
