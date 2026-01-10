import macros.*;

public static void main(String[] args) {
    RawMacro m1 = new RawMacro(
            "FOO",
            new String[] {
                    "int x = 10;",
                    "int y = 20;"
            }
    );

    RawMacro m2 = new RawMacro(
            "BAR",
            new String[] {
                    "System.out.println(x + y);"
            }
    );

    String[] program = {
            "String s = \"FOO\"; FOO_ ffFOOp FOO",
            "FOOFOO BARBAR",
            "BAR",
            "\"FOO BAR\"",
            "FOO BAR"
    };

    for (String line : program) {
        String[] expanded = m1.solution(line);
        for (String l : expanded) {
            String[] expanded2 = m2.solution(l);
            for (String out : expanded2) {
                System.out.println(out);
            }
        }
    }
}
