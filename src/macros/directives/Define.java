package macros.directives;

import common.Context;
import common.Directive;
import macros.Macro;
import parsing.State;


public class Define implements Directive {

    public final Macro macro;

    public Define(Macro macro) {
        this.macro = macro;
    }

    static public int code() {
        return "define".hashCode();
    }

    @Override
    public State process(State lines) {
        Context.macros.define(this.macro);
        lines.skip(this.macro.getDefinition().length);
        return lines;
    }

}
