package IsiLanguage.AST;

import IsiLanguage.DataStructures.IsiVariable;

public class CommandAtribuicao extends AbstractCommands {

	private String id;

	private String expr;

	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public String generateJavaCode() {
		return id + " = " + expr + ";";
	}

	public String generateCCode() {
		return id + " = " + expr + ";";
	}

	@Override
	public String toString() {
		return "CommandAtribuicao [id = " + id + ", expr = " + expr + "]";
	}
}
