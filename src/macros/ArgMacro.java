package macros;

public class ArgMacro extends Macro {

    private RawMacro[] parameters;

    public ArgMacro(String name, String[] definition) {
        super(name, definition);
    }

    @Override
    public int find(String line) {
        return 0;
    }

    @Override
    public String[] solution(String line) {
        return new String[0];
    }
}
