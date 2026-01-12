package macros.directives;

import common.Directive;
import common.Context;
import parsing.State;


public class Undef implements Directive {

    public final String name;

    public Undef(String name) {
        this.name = name;
    }

    public static int code() {
        return "undef".hashCode();
    }

    @Override
    public State process(State lines) {
        Context.macros.undef(this.name);
        lines.skip(1);
        return lines;
    }

}
