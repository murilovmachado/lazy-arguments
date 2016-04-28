package plp.functional1.expression;


import plp.functional1.memory.AmbienteCompilacao;
import plp.functional1.memory.AmbienteExecucao;
import plp.functional1.memory.VariavelJaDeclaradaException;
import plp.functional1.memory.VariavelNaoDeclaradaException;
import plp.functional1.util.Tipo;

public class ValorIrredutivel implements Valor {

	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return null;
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return true;
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return null;
	}

	public Expressao reduzir(AmbienteExecucao ambiente) {
		return this;
	}
	
	public ValorIrredutivel clone() {
		return this;
	}
}
