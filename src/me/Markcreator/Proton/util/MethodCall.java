package me.Markcreator.Proton.util;

public class MethodCall {

	private String method;
	private Object[] args;
	
	public MethodCall(String method, Object... args) {
		this.method = method;
		this.args = args;
	}
	
	public String getMethod() {
		return method;
	}
	
	public Object[] getArgs() {
		return args;
	}
}
