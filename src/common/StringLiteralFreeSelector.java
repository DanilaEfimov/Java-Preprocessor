package common;

public class StringLiteralFreeSelector {

    /**
     * Essential method for skip string literals.
     * @param line java source code line.
     * @param trigger preparing character (e.g. escape token).
     * @param token searched character.
     * @return position of first include of token out of string literals.
     */
    public static int getPosition(String line, char trigger, char token) {
        int idx = 0;

        boolean inLiteral = false;
        boolean escapedClosing = false;
        char[] charArray = line.toCharArray();
        for(char ch : charArray){
            if(++idx >= charArray.length)
                break;

            if(!inLiteral){
                if(ch == trigger && charArray[idx] == token)
                    return idx + 1;
            }

            if(!inLiteral && ch == Config.Java.stringLiteralOpening){
                inLiteral = true;           // turn into string literal
                escapedClosing = false;     // by default closing dropped in general
            }
            else if(escapedClosing){
                escapedClosing = false;     // reset escaped closing
            }
            else if(ch == Config.Java.escapeSymbol
                    && charArray[idx] == Config.Java.stringLiteralClosing) {
                escapedClosing = true;      // set escaped closing flag
            }
            else if(ch == Config.Java.stringLiteralClosing) {
                inLiteral = false;          // turn out string literal
            }
        }

        return Config.npos;
    }

    public static int getPosition(String line, String sub) {
        int idx = line.indexOf(sub);

        while (idx != -1) {
            if (!StringLiteralFreeSelector.inLiteral(line, idx))
                return idx;
            idx = line.indexOf(sub, idx + 1);
        }

        return Config.npos;
    }

    public static boolean hasLiteral(String line) {
        return line.matches(Config.Java.stringLiteral);
    }

    public static String[] getLiterals(String line) {
        return new String[]{};
    }

    public static boolean inLiteral(String line, int pos) {
        boolean in = false;

        for (int i = 0; i < pos && i < line.length(); i++) {
            if (line.charAt(i) == Config.Java.stringLiteralOpening
                && (i == 0 || line.charAt(i - 1) != Config.Java.escapeSymbol))
                in = !in;
        }

        return in;
    }

}
