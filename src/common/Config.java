package common;

public class Config {
    public static final String jppHolder = "@jpp";
    public static final String commentPattern = "//.*";
    public static final String directivePattern = commentPattern + jppHolder + ".*";
    public static final String variableHolderPattern = "";

    public static final String openScopePattern = "\\{";
    public static final String closeScopePattern = "\\}";
    public static final String stringLiteral = "\"((?:[^\"\\\\]|\\\\.)*)\"";
}
