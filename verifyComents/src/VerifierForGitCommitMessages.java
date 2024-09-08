import java.util.Arrays;

public class VerifierForGitCommitMessages {

    private static final String[] VERBS = {"Summarize"};

    public static void main(String[] args) {
        String commitMessage =
                "Summarize changes in around 50 characters or less\n" +
                        "\n" +
                        "More detailed explanatory text, if necessary. Wrap it to about 72\n" +
                        "characters or so. In some contexts, the first line is treated as the\n" +
                        "subject of the commit and the rest of the text as the body. The\n" +
                        "blank line separating the summary from the body is critical (unless\n" +
                        "you omit the body entirely); various tools like `log`, `shortlog`\n" +
                        "and `rebase` can get confused if you run the two together.\n" +
                        "\n" +
                        "Explain the problem that this commit is solving. Focus on why you\n" +
                        "are making this change as opposed to how (the code explains that).\n" +
                        "Are there side effects or other unintuitive consequences of this\n" +
                        "change? Here's the place to explain them.";
        validateGitCommitMessage(commitMessage);
    }

    private static void validateGitCommitMessage(final String commitMessage) {
        String[] lines = splitMessageByLine(commitMessage);


        String subject = lines[0];
        verifyThatSubjectNotEmpty(subject);
        verifyThatSubjectSeparatedFromBody(lines);
        verifyThatSubjectLenghLessThan50chars(subject);
        verifyThatSubjectCapitalized(subject);
        verifyThatSubjectDoesNotEndWithPeriod(subject);
        verifyThatSubjectStartedFromVerbInImperativeMood(subject);
        if (ifBodyFound(lines)) {
            verifyThatEachBodyLineLessThan72Chars(subArray(lines, 2));
            System.out.println("Message is valid");
        }
    }

    private static String[] splitMessageByLine(String commitMessage) {
        return commitMessage.split("\n");
    }


    private static void verifyThatSubjectNotEmpty(String subject) {
        if (subject.isEmpty()) {
            System.out.println("Enter a commit message!");
            System.exit(10);
        }
    }


    private static void verifyThatSubjectLenghLessThan50chars(String subject) {
        if (subject.length() > 50) {
            System.out.println("limit the subject line to 50 characters! Current lenght if" + subject.length());
            System.exit(2);
        }
    }

    private static void verifyThatSubjectCapitalized(String subject) {
        if (!Character.isUpperCase(subject.charAt(0))) {
            System.out.println("Capitalize the subject line!");
            System.exit(3);

        }
    }

    private static void verifyThatSubjectDoesNotEndWithPeriod(String subject) {
        char lastChar = subject.charAt(subject.length() - 1);
        if (lastChar == '.') {
            System.out.println("Do not end the subject line with a" + lastChar + "characters");
            System.exit(4);
        }
    }

    private static void verifyThatSubjectStartedFromVerbInImperativeMood(String subject) {
        String firstWord = getFirstWord(subject);
        if (!ivVerb(firstWord)) {
            System.out.println("The " + firstWord + "is not a verb or is not a verb in imperative mood!");
            System.exit(5);
        }
    }

    private static String getFirstWord(String subject) {
        return subject.split(" ")[0];
    }

    private static boolean ivVerb(String word) {
        for (final String verb : VERBS) {
            if (verb.equals(word)) {
                return true;
            }
        }
        return false;

    }

    private static void verifyThatEachBodyLineLessThan72Chars(String[] lines) {
        for (final String line : lines) {
            if (line.length() > 72) {
                System.out.println("Wrap the body at 72 characters! the folowing line has " + line + "characters" + line.length());
                System.exit(6);
            }
        }
    }

    private static String[] subArray(String[] lines, int startIndex) {
        return Arrays.copyOfRange(lines, startIndex, lines.length);
    }

    private static boolean ifBodyFound(String[] lines) {
        return lines.length > 2;
    }


    private static void verifyThatSubjectSeparatedFromBody(String[] lines) {
        if (lines.length > 1 && !lines[1].isEmpty()) {
            System.out.println("Separate subject from body with a blank line!");
            System.exit(1);
        }
    }
}