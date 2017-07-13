package me.Markcreator.Proton.util;

public class Command {

	private String command;
	private Object[] args;
	
	public Command(String command, Object... args) {
		this.command = command;
		this.args = args;
	}
	
	public String getCommand() {
		return command;
	}
	
	public Object[] getArgs() {
		return args;
	}
}
