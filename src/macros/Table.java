package macros;

import java.util.HashMap;

public class Table {

    static private HashMap<String, Macro> macros;

    public static boolean isDefined(String name) {
        return Table.macros.containsKey(name);
    }

    public static void define(Macro macro) {
        if(Table.isDefined(macro.name))
            System.out.println("Macro.Table: warning: macro '" + macro.name + "' was redefined");

        Table.macros.put(macro.name, macro);
    }

    public static void undef(String name) {
        if(Table.isDefined(name))
            Table.macros.remove(name);
    }

}
