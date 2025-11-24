import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<String> lines = getFileData("src/data");

        int partOneAnswer = 0;
        int partTwoAnswer = 0;
        for (int i = 0; i < lines.size(); i++) {
            partOneAnswer += getPartOneNumber(lines.get(i));
            partTwoAnswer += getPartTwoNumber(lines.get(i));
        }

        System.out.println("Part one answer: " + partOneAnswer);
        System.out.println("Part two answer: " + partTwoAnswer);
    }

       
    public static int getPartOneNumber(String line) {
        char firstNum = '0';
        char lastNum = '0';
        for (int i=0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                firstNum = line.charAt(i);
                break;
            }
        }

        for (int i = line.length() - 1; i >= 0; i--) {
            if (Character.isDigit(line.charAt(i))) {
                lastNum = line.charAt(i);
                break;
            }
        }
        return Integer.parseInt("" + firstNum + lastNum);
    }

    public static int getNumberfromWord(String word) {
        if ((word.equals("zero")) || (word.equals("orez"))) return 0;
        if ((word.equals("one")) || (word.equals("eno"))) return 1;
        if ((word.equals("two")) || (word.equals("owt"))) return 2;
        if ((word.equals("three")) || (word.equals("eerht"))) return 3;
        if ((word.equals("four")) || (word.equals("ruof"))) return 4;
        if ((word.equals("five")) || (word.equals("evif"))) return 5;
        if ((word.equals("six")) || (word.equals("xis")))  return 6;
        if ((word.equals("seven")) || (word.equals("neves"))) return 7;
        if ((word.equals("eight")) || (word.equals("thgie"))) return 8;
        if ((word.equals("nine")) || (word.equals("enin"))) return 9;
        return -1; // Invalid word
    }

    public static String reverseString(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }

    public static int getPartTwoNumber(String line) {
        int firstNumber = 12;
        String[] patterns = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int bestPosition = Integer.MAX_VALUE; 
        String bestMatch = null;
        for (String pattern : patterns) {
            int position = line.indexOf(pattern);
            if (position != -1 && position < bestPosition) {
                bestPosition = position;
                bestMatch = pattern;
            }
        }
        if (bestMatch.length() == 1) {
            firstNumber = Character.getNumericValue(bestMatch.charAt(0));
        } else {
            firstNumber = getNumberfromWord(bestMatch);
        }

        int secondNumber = 25; 
        String reversedLine = reverseString(line);
        String[] reversedPatterns = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "orez", "eno", "owt", "eerht", "ruof", "evif", "xis", "neves", "thgie", "enin"};
        int bestPositionReversed = Integer.MAX_VALUE; 
        String bestMatchReversed = null;
        for (String pattern : reversedPatterns) {
            int position = reversedLine.indexOf(pattern);
            if (position != -1 && position < bestPositionReversed) {
                bestPositionReversed = position;
                bestMatchReversed = pattern;
            }
        }
        if (bestMatchReversed.length() == 1) {
            secondNumber = Character.getNumericValue(bestMatchReversed.charAt(0));
        } else {
            secondNumber = getNumberfromWord(bestMatchReversed);
        }
        int numbersTogether = Integer.parseInt("" + firstNumber + secondNumber);
        return numbersTogether;
    }
    
    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}