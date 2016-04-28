package plp.functional1.expression;

/**
 * Created by murilovmachado on 4/11/16.
 */
public class ArgumentoEager implements ArgumentoFuncao{
    private Id id;

    public ArgumentoEager(String strName) {
        this.id = new Id(strName);
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public boolean isLazy() {
        return false;
    }

    @Override
    public ArgumentoEager clone() {
        return this;
    }
}
