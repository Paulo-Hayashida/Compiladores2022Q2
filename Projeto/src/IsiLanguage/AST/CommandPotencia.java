package IsiLanguage.AST;

import IsiLanguage.DataStructures.IsiVariable;

public class CommandPotencia extends AbstractCommands{
	private IsiVariable var1;
	private IsiVariable var2;
	
	public CommandPotencia(IsiVariable var1, IsiVariable var2)
	{
		this.var1 = var1;
		this.var2 = var2;
	}
	
	public String generateJavaCode()
	{
		return "Math.pow(" + var1.getName() + ", " + var2.getName() + ");";
	}
	
	public String generateCCode()
	{
		return "pow(" + var1 + ", " + var2 + ");";
	}
}
