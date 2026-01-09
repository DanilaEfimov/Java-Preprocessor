package common;

public class StringLiteralFreeCharSelector {

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

}
