package IsiLanguage.AST;

import IsiLanguage.DataStructures.IsiVariable;

public class CommandEscrita extends AbstractCommands {

	private String id;
	private IsiVariable var;

	public CommandEscrita(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}

	@Override
	public String generateJavaCode() {
		return "System.out.println(" + id + ");";
	}

	public String generateCCode() {
		if(var == null)
		{
			return "printf(" + id +"\n);";
		}
		else
		{
			return "printf(\"%" + (var.getType()==IsiVariable.NUMBER? "f" : "s") + "\", " + id + ");";
		}
	}

	@Override
	public String toString() {
		return "CommandEscrita [id = " + id + "]";
	}
}
