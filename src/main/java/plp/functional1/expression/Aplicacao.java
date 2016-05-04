//Mudar o for do iterator para o novo for.
package plp.functional1.expression;

import plp.functional1.memory.*;
import plp.functional1.util.DefFuncao;
import plp.functional1.util.Tipo;
import plp.functional1.util.TipoFuncao;

import java.util.*;

import static java.util.Arrays.asList;
import static plp.functional1.util.ToStringProvider.listToString;

public class Aplicacao implements Expressao {

	private Id func;
	private List<? extends Expressao> argsExpressao;

	public Aplicacao(Id f, Expressao... expressoes) {
		this(f, asList(expressoes));
	}

	public Aplicacao(Id f, List<? extends Expressao> expressoes) {
		func = f;
		argsExpressao = expressoes;
	}

	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		AmbienteExecucaoFuncional ambienteFuncional = (AmbienteExecucaoFuncional) ambiente;

		DefFuncao funcao;
		try {
			funcao = ambienteFuncional.getFuncao(func);
		} catch (IdentificadorNaoDeclaradoException e) {
			throw new VariavelJaDeclaradaException(func);
		}

		Map<Id, Expressao> mapIdValor = resolveParametersBindings(ambiente, funcao);

		ambiente.incrementa();

		includeValueBindings(ambiente, mapIdValor);

		Valor vresult = funcao.getExp().avaliar(ambiente);
		ambiente.restaura();
		return vresult;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean result;
		Tipo aux = ambiente.get(func);

		if (aux instanceof TipoFuncao) {
			TipoFuncao tipoFuncao = (TipoFuncao) aux;

			result = tipoFuncao.checaTipo(ambiente, argsExpressao);
		} else {
			// A fun��o func n�o foi declarada.

			// TODO: lan�ar uma exce��o ou separar vari�veis de fun��es no
			// contexto de compila��o.

			result = false;
		}
		return result;
	}

	/**
	 * Returns the args.
	 * 
	 * @return ListaExpressao
	 */
	public List<? extends Expressao> getArgsExpressao() {
		return argsExpressao;
	}

	/**
	 * Returns the func.
	 * 
	 * @return Id
	 */
	public Id getFunc() {
		return func;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 * @precondition this.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		TipoFuncao tipoFuncao = (TipoFuncao) ambiente.get(func);
		return tipoFuncao.getTipo(ambiente, argsExpressao);
	}

	private void includeValueBindings(AmbienteExecucao ambiente,
			Map<Id, Expressao> mapIdValor) throws VariavelJaDeclaradaException {
		for (Map.Entry<Id, Expressao> mapeamento : mapIdValor.entrySet()) {
			Id id = mapeamento.getKey();
			Expressao expressao = mapeamento.getValue();
			ambiente.map(id, expressao);
		}
	}

	private Map<Id, Expressao> resolveParametersBindings(AmbienteExecucao ambiente,
			DefFuncao funcao) throws VariavelNaoDeclaradaException,
			VariavelJaDeclaradaException {
		List<ArgumentoFuncao> parametrosId = funcao.getListaId();
		List<? extends Expressao> expressoesValorReal = argsExpressao;

		Map<Id, Expressao> mapIdValor = new HashMap<>();

		Iterator<? extends Expressao> iterExpressoesValor = expressoesValorReal
				.iterator();
		//TODO: Se parametro for lazy, não avaliar, salvar toda a expressao no map
		for (ArgumentoFuncao arg : parametrosId) {
			Expressao exp = iterExpressoesValor.next();
			Expressao valorOuExpressao;

			if(arg.isLazy()) {
				valorOuExpressao = exp;
			} else {
				valorOuExpressao = exp.avaliar(ambiente);
			}

			mapIdValor.put(arg.getId(), valorOuExpressao);
		}
		return mapIdValor;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 * 
	 * @return uma representacao String desta expressao.
	 */
	@Override
	public String toString() {
		return String.format("%s(%s)", func, listToString(argsExpressao, ","));
	}

	public Expressao reduzir(AmbienteExecucao ambiente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Aplicacao clone() {
		Aplicacao retorno;
		ArrayList<Expressao> novaLista = new ArrayList<Expressao>(this.argsExpressao.size());

		Iterator<? extends Expressao> iterator = argsExpressao.iterator();
		while (iterator.hasNext()){
			Expressao exp = iterator.next();
			novaLista.add(exp.clone());			
		}
		
		retorno = new Aplicacao(this.func.clone(), novaLista);
		
		return retorno;
	}
}
