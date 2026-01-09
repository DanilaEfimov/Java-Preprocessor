package exceptions;

import java.util.regex.Pattern;

public class MatchRequiredException extends MissmatchException {

    public MatchRequiredException(String line, Pattern pattern) {
        this.sourceLine = line;
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "was required match for line: '" + this.sourceLine
                + "' and pattern: '" + this.pattern + "'";
    }

}
