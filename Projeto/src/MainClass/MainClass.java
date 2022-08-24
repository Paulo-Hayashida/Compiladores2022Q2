package MainClass;

import IsiLanguage.Exception.IsiSemanticException;
import IsiLanguage.Parser.IsiLangLexer;
import IsiLanguage.Parser.IsiLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;

			//lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			//lexer = new IsiLangLexer(CharStreams.fromFileName("entrada.isi"));
			//lexer = new IsiLangLexer(CharStreams.fromFileName("ErroSemantico.isi"));
			//lexer = new IsiLangLexer(CharStreams.fromFileName("VarNaoDecl.isi"));
			lexer = new IsiLangLexer(CharStreams.fromFileName("TesteExp.isi"));
			
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			parser = new IsiLangParser(tokenStream);

			parser.prog();

			System.out.println("Compilation Successful");

			parser.exibeComandos();

			parser.generateCode();

			parser.generateCCode();
		} catch (IsiSemanticException ex) {
			System.err.println("Semantic Error - " + ex.getMessage());
		} catch (Exception ex) {
			System.err.println("ERROR " + ex.getMessage());
		}
	}
}
