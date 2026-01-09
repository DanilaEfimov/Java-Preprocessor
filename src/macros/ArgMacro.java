package macros;

public class ArgMacro extends Macro {

    private RawMacro[] parameters;

    ArgMacro(String name, String[] definition) {
        super(name, definition);
    }

    @Override
    public String[] solution(String line) {
        return new String[0];
    }
}
