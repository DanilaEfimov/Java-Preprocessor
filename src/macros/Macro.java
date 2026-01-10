package macros;

import common.Config;
import common.StringLiteralSelector;

public abstract class Macro {

    protected String name;
    protected String[] definition;

    Macro(String name, String[] definition) {
        this.name = name;
        this.definition = definition;
    }

    public abstract int find(String line);

    public abstract String[] solution(String line);

    public String getName() {
        return this.name;
    }

    public String[] getDefinition() {
        return this.definition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefinition(String[] definition) {
        this.definition = definition;
    }
}
