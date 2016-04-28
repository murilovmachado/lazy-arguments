package plp.functional1.memory;

import plp.functional1.expression.Id;

public class VariavelJaDeclaradaException extends IdentificadorJaDeclaradoException{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VariavelJaDeclaradaException(Id id){
		super("Vari�vel " + id + " j� declarada.");
	}
}
