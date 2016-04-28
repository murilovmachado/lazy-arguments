package plp.functional1.memory;

import plp.functional1.expression.Expressao;
import plp.functional1.expression.Id;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;


public class ContextoExecucao extends Contexto<Expressao>
        implements AmbienteExecucao {

	public ContextoExecucao clone() {
		ContextoExecucaoFuncional retorno = new ContextoExecucaoFuncional();
		
		Stack<HashMap<Id, Expressao>> novaPilha = new Stack<>();
		
		HashMap<Id, Expressao> novoMap = new HashMap<>();
		novaPilha.add(novoMap);

		for (HashMap<Id, Expressao> map : this.pilha){
			for(Entry<Id, Expressao> entry : map.entrySet()){
				novoMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		retorno.setPilha(novaPilha);
		
		return retorno;
	}
}
