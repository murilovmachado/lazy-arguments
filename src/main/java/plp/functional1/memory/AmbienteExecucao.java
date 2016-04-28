package plp.functional1.memory;

import plp.functional1.expression.Expressao;


public interface AmbienteExecucao extends Ambiente<Expressao> {

	public AmbienteExecucao clone();

}
