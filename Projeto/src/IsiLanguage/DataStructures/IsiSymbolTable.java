package IsiLanguage.DataStructures;

import java.util.ArrayList;
import java.util.HashMap;

public class IsiSymbolTable {

	private HashMap<String, IsiSymbol> map;

	public IsiSymbolTable() {
		map = new HashMap<String, IsiSymbol>();
	}

	public void add(IsiSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}

	public boolean exists(String symbolname) {
		return map.get(symbolname) != null;
	}

	public IsiSymbol get(String symbolname) {
		return map.get(symbolname);
	}

	public ArrayList<IsiSymbol> getAll() {
		ArrayList<IsiSymbol> lista = new ArrayList<IsiSymbol>();
		for (IsiSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}
}
