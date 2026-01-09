package macros;

public abstract class Macro {

    public final String name;
    public final String[] definition;

    Macro(String name, String[] definition) {
        this.name = name;
        this.definition = definition;
    }

    public abstract String[] solution(String line);

    public String getName() {
        return name;
    }

    public String[] getDefinition() {
        return definition;
    }
}
