package common;

public class Config {

    public static final int npos = -1;

    public static class Jpp {
        public static final String jppHolder = "@jpp";
        public static final String directivePattern = jppHolder + ".*";
        public static final String macroDefSeparatorRegex = "\\\\";
        public static final String macroDefSeparator = "\\";
        public static final String macroDefSeparatorPattern = ".*" + macroDefSeparatorRegex + "$";
    }

    public static class General {
        public static final String lineSeparator = "\n";
        public static final String wordPattern = "\\w+";
        public static final String spacePattern = "\\s+";
        public static final String separatedWords = wordPattern + spacePattern + wordPattern;
        public static final String openBracket = "(";
        public static final String closeBracket = ")";
    }

    public static class Java {
        public static final String multiCommentOpening = "/*";
        public static final String multiCommentClosing = "*/";
        public static final String singleCommentOpening = "//";
        public static final String singleCommentPattern = "//.*";

        public static final char operatorSeparator = ';';
        public static final char openScope = '{';
        public static final char closeScope = '}';

        public static final char escapeSymbol = '\\';
        public static final char stringLiteralOpening = '\"';
        public static final char stringLiteralClosing = stringLiteralOpening;
        public static final String stringLiteral =
                stringLiteralOpening
                + "((?:[^\"\\\\]|\\\\.)*)"
                + stringLiteralClosing;

        public static final String identifierPattern = "[a-zA-Z_]\\w+";
        public static final String classKeyWord = "class";
        public static final String classDefinitionPattern =
                classKeyWord + General.spacePattern + identifierPattern;
        public static final String memberFunctionDefinitionPattern =
                Java.identifierPattern + "\\s*\\(("
                + General.separatedWords + "\\s*,\\s*)*"
                + General.separatedWords + "\\s*\\)";
        public static final String scopeSymbolDefinitionPattern =
                classDefinitionPattern + "|" + memberFunctionDefinitionPattern;
    }
}
