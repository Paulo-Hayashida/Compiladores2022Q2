package IsiLanguage.AST;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import IsiLanguage.DataStructures.IsiSymbol;
import IsiLanguage.DataStructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable vartable;
	private ArrayList<AbstractCommands> comandos;
	private String programname;

	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("import java.lang.Math;\n");
		str.append("public class MainClass{\n");
		
		str.append("	public static void main (String args[]){\n");
		str.append("		Scanner _key = new Scanner(System.in);\n");
		for (IsiSymbol symbol : vartable.getAll()) {
			str.append(symbol.generateJavaCode() + "\n");
		}
		for (AbstractCommands comandos : comandos) {
			str.append(comandos.generateJavaCode() + "\n");
		}
		str.append("	}\n");
		str.append("}");

		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		} catch (Exception ex) {
			System.err.println("ERRO - " + ex.getMessage());
		}
	}

	public void generateCTarget() {
		StringBuilder str = new StringBuilder();
		str.append("#include <stdio.h>\n");
		str.append("#include <stdlib.h>\n");
		str.append("#include <math.h>\n");
		str.append("int main() {\n");
		for (IsiSymbol symbol : vartable.getAll()) {
			str.append(symbol.generateCCode() + "\n");
		}
		for (AbstractCommands cmd : comandos) {
			str.append(cmd.generateCCode() + "\n");
		}
		str.append("return 0;\n");
		str.append("}");

		try {
			FileWriter fr = new FileWriter(new File("Main.c"));
			fr.write(str.toString());
			fr.close();
		} catch (Exception ex) {
			System.err.println("ERRO - " + ex.getMessage());
		}
	}

	public IsiSymbolTable getVartable() {
		return vartable;
	}

	public void setVartable(IsiSymbolTable vartable) {
		this.vartable = vartable;
	}

	public ArrayList<AbstractCommands> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommands> comandos) {
		this.comandos = comandos;
	}

	public String getProgramname() {
		return programname;
	}

	public void setProgramname(String programname) {
		this.programname = programname;
	}

}
