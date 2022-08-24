package IsiLanguage.AST;

import IsiLanguage.DataStructures.IsiVariable;

public class CommandRaiz extends AbstractCommands{
	private IsiVariable var1;
	private IsiVariable var2;
	
	public CommandRaiz(IsiVariable var1, IsiVariable var2)
	{
		this.var1 = var1;
		this.var2 = var2;
	}
	
	public String generateJavaCode()
	{
		return "Math.sqrt(" + var1.getName() + ");";
	}
	
	public String generateCCode()
	{
		return "sqrt(" + var1 + ");";
	}
}
