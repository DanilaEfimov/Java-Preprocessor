import macros.*;

public static void main(String[] args) {
    ArgMacro macro = new ArgMacro(
            "SUM",
            new String[]{"a + b"},
            new String[]{"a", "b"}
    );

    String[] program = {
            "dadsd asd asdas asd asd adS SUM (1, 2)",
            "SUM SUM SUM SUM(123123123, 2312)"
    };

    for(String line : program)
        System.out.print(String.join("\n", macro.solution(line)));
}
