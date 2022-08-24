package IsiLanguage.AST;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommands {

	private String condition;
	private ArrayList<AbstractCommands> laco;

	public CommandEnquanto(String condition, ArrayList<AbstractCommands> b) {
		this.condition = condition;
		this.laco = b;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while(" + condition + ") {\n");
		for (AbstractCommands cmd : laco) {
			str.append(cmd.generateJavaCode() + "\n");
		}
		str.append("\n}\n");
		return str.toString();
	}

	public String generateCCode() {
		StringBuilder str = new StringBuilder();
		str.append("while(" + condition + ") {\n");
		for (AbstractCommands cmd : laco) {
			str.append(cmd.generateCCode() + "\n");
		}
		str.append("\n}\n");
		return str.toString();
	}

	@Override
	public String toString() {
		return "CommandEnquanto [id = " + condition + ", laco= " + laco + "]";
	}
}
