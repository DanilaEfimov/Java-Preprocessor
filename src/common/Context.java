package common;

import macros.Table;

public class Context {

    static public Table macros;

    static {
        Context.macros = new Table();
    }

    static int getDefinitionLength(String name){
        if(!Context.macros.isDefined(name))
            return 0;

        return Context.macros.definitionLength(name);
    }

}
