package common;

import exceptions.MatchRequiredException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SymbolSelector {

    /**
     * Scope symbol is class or member function definition.<br>
     * <i>Look at common.Config.java</i>.
     * @param line java source code.
     * @return <b>true</b> if line contains scope symbol definition.
     */
    public static boolean hasScopeSymbolDefinition(String line) {
        Pattern pattern = Pattern.compile(Config.Java.scopeSymbolDefinitionPattern);
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    public static boolean hasClassDefinition(String line) {
        Pattern pattern = Pattern.compile(Config.Java.classDefinitionPattern);
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    public static String getClassName(String line) throws MatchRequiredException {
        String[] words = line.split(Config.General.spacePattern);
        if(words.length == 0)
            throw new RuntimeException("Not enough words in expression: " + line);

        if(SymbolSelector.hasClassDefinition(line)) {
            if (words.length < 2)
                throw new RuntimeException("Not enough words in expression: " + line);

            int idx = 0;
            for(; idx < words.length; idx++)
                if(words[idx].equals(Config.Java.classKeyWord))
                    break;

            if(++idx < words.length){
                Pattern pattern = Pattern.compile(Config.Java.identifierPattern);
                Matcher matcher = pattern.matcher(words[idx]);

                if(matcher.find())
                    return matcher.group();
            }
        }

        throw new MatchRequiredException(line, Pattern.compile(Config.Java.classDefinitionPattern));
    }

    public static boolean hasMemberFunctionDefinition(String line) {
        Pattern pattern = Pattern.compile(Config.Java.memberFunctionDefinitionPattern);
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    public static String getMemberFunctionName(String line) throws MatchRequiredException {
        String[] words = line.split(Config.General.spacePattern);
        if(words.length == 0)
            throw new RuntimeException("Not enough words in expression: " + line);

        if(SymbolSelector.hasMemberFunctionDefinition(line)) {
            if (words.length < 2)
                throw new RuntimeException("Not enough words in expression: " + line);

            int idx = 0;
            while (idx < words.length && words[idx].matches(Config.Java.identifierPattern))
                idx++;

            if(idx >= words.length)
                throw new RuntimeException("can not find member function name");

            Pattern pattern = Pattern.compile(Config.Java.identifierPattern);
            Matcher matcher = pattern.matcher(words[idx]);

            if(matcher.find())
                return matcher.group();
        }

        throw new MatchRequiredException(line, Pattern.compile(Config.Java.classDefinitionPattern));
    }

}
