package IsiLanguage.AST;

import IsiLanguage.DataStructures.IsiVariable;

public class CommandLogaritmo extends AbstractCommands{
	
		private IsiVariable var1;
		private IsiVariable var2;
		
		public CommandLogaritmo(IsiVariable var1, IsiVariable var2)
		{
			this.var1 = var1;
			this.var2 = var2;
		}
		
		public String generateJavaCode()
		{
			return "Math.log(" + var1.getName() + ");";
		}
		
		public String generateCCode()
		{
			return "log(" + var1 + ");";
		}
	}
