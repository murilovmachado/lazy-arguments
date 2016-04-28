package plp.functional1.memory;

import plp.functional1.expression.Id;


public interface Ambiente<T> {

	public void incrementa();

	public void restaura();

	public void map(Id idArg, T tipoId) throws VariavelJaDeclaradaException;

	public T get(Id idArg) throws VariavelNaoDeclaradaException;

}
