package plp.functional1.expression;

/**
 * Created by murilovmachado on 4/11/16.
 */
public class ArgumentoLazy implements ArgumentoFuncao{
    private Id id;

    public ArgumentoLazy(String strName) {
        this.id = new Id(strName);
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public boolean isLazy() {
        return true;
    }

    @Override
    public ArgumentoLazy clone() {
        return this;
    }
}
