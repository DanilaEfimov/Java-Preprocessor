package exceptions;

import java.util.regex.Pattern;

public abstract class MissmatchException extends Exception {

    protected String sourceLine;
    protected Pattern pattern;

    @Override
    public String toString() {
        return "expression '" + this.sourceLine
                + "' missmatchs " + this.pattern;
    }

}
