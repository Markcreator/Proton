package me.Markcreator.Proton.util;

public class MethodCall {

	private String command;
	private Object[] args;
	
	public MethodCall(String command, Object... args) {
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
