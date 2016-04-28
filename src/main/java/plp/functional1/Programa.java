package plp.functional1;

import plp.functional1.expression.Expressao;
import plp.functional1.expression.Valor;
import plp.functional1.memory.*;

public class Programa {

	private Expressao exp;

	public Programa(Expressao exp) {
		this.exp = exp;
	}

	public Valor executar()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		AmbienteExecucaoFuncional ambExec = new ContextoExecucaoFuncional();
		return exp.avaliar(ambExec);
	} 

	public boolean checaTipo()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		AmbienteCompilacao ambComp = new ContextoCompilacao();
		return exp.checaTipo(ambComp);
	}

	public Expressao getExpressao() {
		return exp;
	}

}
