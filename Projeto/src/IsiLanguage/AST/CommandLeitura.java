package IsiLanguage.AST;

import IsiLanguage.DataStructures.IsiVariable;

public class CommandLeitura extends AbstractCommands {
	private String id;
	private IsiVariable var;

	public CommandLeitura(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}

	@Override
	public String generateJavaCode() {
		return id + " = _key." + (var.getType() == IsiVariable.NUMBER ? "nextDouble();" : "nextLine();");
	}

	public String generateCCode() {
		return "scanf(" + (var.getType() == IsiVariable.NUMBER ? "\"%f\", &" : "\"%s\", ") + id + ");";
	}

	@Override
	public String toString() {
		return "CommandLeitura [id = " + id + "]";
	}
}
