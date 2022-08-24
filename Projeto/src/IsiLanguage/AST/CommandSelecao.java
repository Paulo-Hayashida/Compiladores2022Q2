package IsiLanguage.AST;

import java.util.ArrayList;

public class CommandSelecao extends AbstractCommands {

	private String condition;
	private ArrayList<AbstractCommands> listaTrue;
	private ArrayList<AbstractCommands> listaFalse;

	public CommandSelecao(String condition, ArrayList<AbstractCommands> lt, ArrayList<AbstractCommands> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("if(" + condition + ") {\n");
		for (AbstractCommands cmd : listaTrue) {
			str.append(cmd.generateJavaCode() + "\n");
		}
		str.append("\n}\n");
		if (listaFalse.size() > 0) {
			str.append("else {\n");
			for (AbstractCommands cmd : listaFalse) {
				str.append(cmd.generateJavaCode() + "\n");
			}
			str.append("\n}\n");
		}
		return str.toString();
	}

	public String generateCCode() {
		StringBuilder str = new StringBuilder();
		str.append("if(" + condition + ") {\n");
		for (AbstractCommands cmd : listaTrue) {
			str.append(cmd.generateCCode() + "\n");
		}
		str.append("\n}\n");
		if (listaFalse.size() > 0) {
			str.append("else {\n");
			for (AbstractCommands cmd : listaFalse) {
				str.append(cmd.generateCCode() + "\n");
			}
			str.append("\n}\n");
		}
		return str.toString();
	}

	@Override
	public String toString() {
		return "CommandSelecao [id = " + condition + ",listaTrue = " + listaTrue + ",listaFalse = " + listaFalse + "]";
	}
}
