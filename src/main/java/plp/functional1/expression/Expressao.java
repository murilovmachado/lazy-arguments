package plp.functional1.expression;

import plp.functional1.util.Tipo;
import plp.functional1.memory.AmbienteCompilacao;
import plp.functional1.memory.AmbienteExecucao;
import plp.functional1.memory.VariavelJaDeclaradaException;
import plp.functional1.memory.VariavelNaoDeclaradaException;

/** 
 * Uma expressao &eacute; a unidade basica na Linguagem de Expressoes.
 */
public interface Expressao {

	/**
	 * Avalia a expressao retornando seu Valor.
	 */
	Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *          e tipos.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 */
	boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *          e tipos.
	 * @return os tipos possiveis desta expressao.
	 */
	Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;

	/**
	 * 
	 * @param ambiente
	 * @return Express�o reduzida sem ocorr�ncia de ids conhecidas.
	 */
	Expressao reduzir(AmbienteExecucao ambiente);

	/**
	 * 
	 * @return
	 */
	public Expressao clone();
}
