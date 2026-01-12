package macros;

import java.util.HashMap;

public class Table {

    private HashMap<String, Macro> macros;

    public Table() {
        this.macros = new HashMap<>();
    }

    public HashMap<String, Macro> get() {
        return this.macros;
    }

    public boolean isDefined(String name) {
        return this.macros.containsKey(name);
    }

    public int definitionLength(String name){
        if(!this.isDefined(name))
            return 0;

        return this.macros.get(name).getDefinition().length;
    }

    public String[] getDefinition(String name) {
        if(!this.isDefined(name))
            return new String[0];

        return this.macros.get(name).getDefinition();
    }

    public void define(Macro macro) {
        if(this.isDefined(macro.name))
            System.out.println("Macro.Table: warning: macro '" + macro.name + "' was redefined");

        this.macros.put(macro.name, macro);
    }

    public void undef(String name) {
        if(this.isDefined(name))
            this.macros.remove(name);
    }

}
