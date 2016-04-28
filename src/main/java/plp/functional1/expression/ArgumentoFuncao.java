package plp.functional1.expression;

/**
 * Created by murilovmachado on 4/11/16.
 */
public interface ArgumentoFuncao {
    Id getId();
    boolean isLazy();
    ArgumentoFuncao clone();
}
