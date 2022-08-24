grammar IsiLang;

@header
{
	import IsiLanguage.DataStructures.*;
	import IsiLanguage.Exception.*;
	import IsiLanguage.AST.*;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members
{
	private int _tipo;
	private String _varname;
	private String _varvalue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommands> curThread;
	private Stack<ArrayList<AbstractCommands>> stack = new Stack<ArrayList<AbstractCommands>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprSelection;
	private String _exprWhile;
	private ArrayList<AbstractCommands> listaTrue;
	private ArrayList<AbstractCommands> listaFalse;
	private ArrayList<AbstractCommands> blocoWhile;
	private IsiVariable var1;
	private IsiVariable var2;
	private IsiVariable exp;
	private IsiVariable usedID;
	private String pow;
	private String sqrt;
	private String log;
	
	public void verifica (String id)
	{
		if (!symbolTable.exists(id))
		{
			throw new IsiSemanticException("Variable " + id + " not declared");
		}
	}
	
	public void exibeComandos()
	{
		for(AbstractCommands c : program.getComandos())
		{
			System.out.println(c);
		}
	}
	
	public void generateCode()
	{
		program.generateTarget();
	}
	
	public void generateCCode()
	{
		program.generateCTarget();
	}
}

prog 	: 'programa'	decl	bloco	'fimprog;'
		{	
			program.setVartable(symbolTable);
			program.setComandos(stack.pop());
			for(IsiSymbol symbol : symbolTable.getAll())
			{
				//System.out.println(symbol);
				IsiVariable variable = (IsiVariable) symbol;
				if(variable.getValue() == "Nao usado")
				{
					System.out.println("[WARNING]: Variavel " + variable.getName() + " declarada, mas nao utilizada");
				}
			}
		} 
		;
		

decl	: (declaravar)+		
		;
		
declaravar 	: tipo ID 	{
						_varname = _input.LT(-1).getText();
						_varvalue = "Nao usado";
						symbol = new IsiVariable(_varname, _tipo, _varvalue);
						if(!symbolTable.exists(_varname))
							{
								symbolTable.add(symbol);
							}
						else
							{
								throw new IsiSemanticException("Variable " + _varname + " already declared");
							}
						}
			(VIR
			ID			{
						_varname = _input.LT(-1).getText();
						_varvalue = "Nao usado";
						symbol = new IsiVariable(_varname, _tipo, _varvalue);
						if(!symbolTable.exists(_varname))
							{
								symbolTable.add(symbol);
							}
						else
							{
							throw new IsiSemanticException("Variable " + _varname + " already declared");
							}
						}
			)* SC
			;

tipo 	:	'numero'	{_tipo = IsiVariable.NUMBER;}
		| 	'texto'		{_tipo = IsiVariable.TEXT;}
		;

bloco	: 	{
				curThread = new ArrayList<AbstractCommands>();
				stack.push(curThread);
			} 
		(cmd)+
		;
		
cmd		: cmdleitura 
		| cmdescrita
		| cmdattrib
		| cmdselecao
		| cmdenquanto
		;
		
cmdleitura 	: 'leia' AP
					 ID {verifica(_input.LT(-1).getText());
					 	_readID = _input.LT(-1).getText();
					 	usedID = (IsiVariable) (symbolTable.get(_readID));
						usedID.setValue("Usado");
					 	}
					 FP
					 SC
			{
				IsiVariable var = (IsiVariable)symbolTable.get(_readID);
				CommandLeitura cmd = new CommandLeitura(_readID, var);
				stack.peek().add(cmd);
			}
			;
			
cmdescrita 	: 'escreva' AP
						(ID 	{verifica(_input.LT(-1).getText());
								_writeID = _input.LT(-1).getText();
								}
						| STRING	{_writeID = _input.LT(-1).getText();}
						)
						FP
						SC
			{
				IsiVariable writev = (IsiVariable)symbolTable.get(_writeID);
				CommandEscrita cmd = new CommandEscrita(_writeID, writev);
				stack.peek().add(cmd);
			}
			;
			
cmdattrib 	: 	ID 	{verifica(_input.LT(-1).getText());
					_exprID = _input.LT(-1).getText();
					usedID = (IsiVariable) (symbolTable.get(_exprID));
					usedID.setValue("Usado");
					IsiVariable expID = (IsiVariable)symbolTable.get(_exprID);
					}
				ATTR	{
							_exprContent = "";
						}
				expr 	{
							if(var2 != null)
							{
								if(var2.getType() != 3 && var2.getType() != 0)
								{
									exp = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
								}
								else
								{
									var2.setType(0);
									exp = var2;
								}
							}
							else
							{
								exp = var1;
							}
						}	
				SC
			{
				if(expID.getType() != exp.getType())
				{
					throw new IsiSemanticException("Variaveis incompativeis");
				}
				CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
				stack.peek().add(cmd);
				var1 = null;
				var2 = null;
			}
			
			;

cmdselecao	:	'se' 	AP 
					 	ID 	{verifica(_input.LT(-1).getText());
					 		_exprSelection = _input.LT(-1).getText();
					 		}
					 	OPREL {_exprSelection += _input.LT(-1).getText();}
					 	(ID {verifica(_input.LT(-1).getText());}
					 	| NUMBER) {_exprSelection += _input.LT(-1).getText();}
					 	FP 
					 	ACH 
					 	{	
					 		curThread = new ArrayList<AbstractCommands>();
					 		stack.push(curThread);
					 	}
					 	(cmd)+ 
					 	FCH
					 	{
					 		listaTrue = stack.pop();
					 	}
				('senao' ACH 
						{
							curThread = new ArrayList<AbstractCommands>();
							stack.push(curThread);
						}
						(cmd)+ 
						FCH
						{
							listaFalse = stack.pop();
							CommandSelecao cmd = new CommandSelecao(_exprSelection, listaTrue, listaFalse);
							stack.peek().add(cmd);
						}
						)?
			;

cmdenquanto	: 'enquanto' 	AP
							ID 	{
									verifica(_input.LT(-1).getText());
									_exprWhile = _input.LT(-1).getText();
								} 
							OPREL 	{_exprWhile += _input.LT(-1).getText();}
							(ID	{verifica(_input.LT(-1).getText());}
							 | NUMBER) {_exprWhile += _input.LT(-1).getText();}
							 FP 
							 ACH 
							 {
							 	curThread = new ArrayList<AbstractCommands>();
							 	stack.push(curThread);
							 }
							 (cmd)+
							 FCH
							 {
							 	blocoWhile = stack.pop();
							 	CommandEnquanto cmd = new CommandEnquanto(_exprWhile, blocoWhile);
							 	stack.peek().add(cmd);
							 }
			;

expr		: 	termo 	{
						if(var1 == null)
							{
							var1 = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
							}
						}
				( 
				OP		{_exprContent += _input.LT(-1).getText();
						if(_input.LT(-1).getText().equals("^"))
							{
							pow = "potencia";
							}
						
						if(_input.LT(-1).getText().equals("sqrt"))
							{
							sqrt = "raiz";
							}
						if(_input.LT(-1).getText().equals("log"))
							{
							log = "logaritmo";
							}
						}	
			 	termo 	{
			 			if(var2 != null)
			 				{
			 					if(var2.getType() != 3)
			 					{
			 						var2 = (IsiVariable)symbolTable.get(_input.LT(-1).getText());
			 					}
			 					else
			 					{
			 						var2.setType(0);
			 					}
			 				}
			 			}
			 	)*
			 			{
			 				if(var2 != null)
			 				{	
			 					if(var1.getType() == 1 || ((var1.getType() == 0 || var1.getType() == 3) && var2.getType() != var1.getType()))
			 					{
			 						throw new IsiSemanticException("Operacao com variaveis compativeis");
			 					}
			 				}
			 				if(pow != null)
			 				{
			 					if(pow.equals("potencia"))
								{
									CommandPotencia pow = new CommandPotencia(var1, var2);
									stack.peek().add(pow);
								}
							}
							if(sqrt != null)
			 				{
			 					if(sqrt.equals("raiz"))
								{
									CommandRaiz sqrt = new CommandRaiz(var1, var2);
									stack.peek().add(sqrt);
								}
							}
							if(log != null)
			 				{
			 					if(log.equals("logaritmo"))
								{
									CommandLogaritmo log = new CommandLogaritmo(var1, var2);
									stack.peek().add(log);
								}
							}
							pow = null;
							sqrt = null;
							log = null;
						}
			;

termo 		: ID 	{verifica(_input.LT(-1).getText());
					_exprContent += _input.LT(-1).getText();
					} 
			| NUMBER	{_exprContent += _input.LT(-1).getText();
						if(var1 == null)
							{
							var1 = new IsiVariable(_input.LT(-1).getText(), 0, null);
							}
						else if (_input.LT(1).getText() != ";")
							{
							var2 = new IsiVariable(_input.LT(-1).getText(), 3, null);
							}
						}
			| STRING	{_exprContent += _input.LT(-1).getText();
						var1 = new IsiVariable(_input.LT(-1).getText(), 1, null);
						}
			/*
			|
			(OPRL		{_exprContent += _input.LT(-1).getText();
						}
			AP
			(ID 		{verifica(_input.LT(-1).getText());
						_exprContent += _input.LT(-1).getText();}
			| NUMBER	{_exprContent += _input.LT(-1).getText();
						var2 = new IsiVariable(_input.LT(-1).getText(), 3, null);
						}
			)		
			FP
			)
			*/
			;



AP 	: '('
	;
	
FP 	: ')'
	;
	
SC 	: ';'
	;
	
OP 	: '+' | '-' | '*' | '/' | '^' | 'sqrt' | 'log'
	;

/*
OPRL 	: 'sqrt' | 'log'
		;
*/

ATTR : '='
	 ;
	 
VIR 	: ','
		;

ACH 	: '{'
		;
		
FCH		: '}'
		;

OPREL	: '>' | '<' | '>=' | '<=' | '==' | '!='
		;

ID 	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER 	: [0-9]+ ('.' [0-9]+)?
		;

STRING 	: '"' ([a-z] | [A-Z] | [0-9] | ' ')+ '"'
		;

WS 	: (' ' | '\t' | '\n' | '\r') -> skip
	;