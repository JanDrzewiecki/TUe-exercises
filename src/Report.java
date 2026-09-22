import javax.management.StringValueExp;
import java.util.Arrays;
import java.util.Scanner;

public class Report {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Names of the three water quality indicators.
        String[] indicators = { "Do", "pH", "Temperature" };

        // Read the analysis mode.
        int mode = scanner.nextInt();

        // Consume the remaining newline.
        scanner.nextLine();

        // This array will store all scores for the three indicators.
        int[][] allScores = null;

        // Read one line of scores for each indicator.
        for (int i = 0; i < indicators.length; i++) {

            // Split the input line into individual values.
            String[] values = scanner.nextLine().trim().split("\\s+");

            // Create the 2D array after reading the first line.
            if (allScores == null) {
                allScores = new int[indicators.length][values.length];
            }

            // Convert the values from Strings to integers.
            for (int j = 0; j < values.length; j++) {
                allScores[i][j] = Integer.parseInt(values[j]);
            }
        }

        scanner.close();

        System.out.println();

        // Create a Report object.
        Report report = new Report();

        // Generate a report for each indicator.
        for (int i = 0; i < indicators.length; i++) {
            report.generateReport(indicators[i], allScores[i], mode);
        }
    }


    public double computeAverage(int[] scores) {
        double average = 0;
        for (int i = 0; i < scores.length; i += 1) {
            average += scores[i];
        }
        average = average / 4;
        return average;
    }


    public double computeMedian(int[] scores) {
        int[] scoresCopy = scores.clone();
        Arrays.sort(scoresCopy);
        if (scoresCopy.length % 2 == 1) {
            return scoresCopy[(scoresCopy.length / 2) - 1];
        } else {
            double median = scoresCopy[scoresCopy.length / 2] + scoresCopy[(scoresCopy.length / 2) - 1];
            return median / 2;
        }
    }


    public int findHighestScore(int[] scores) {
        int highest = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > highest) {
                highest = scores[i];
            }
        }
        return highest;
    }


    public String letterGrade(int[] scores) {
        double average = computeAverage(scores);
        String grade = "";
        if (average >= 90 && average <= 100) {
            grade = "O";
        } else if (average >= 80 && average < 90) {
            grade = "E";
        } else if (average >= 70 && average < 80) {
            grade = "A";
        } else if (average >= 60 && average < 70) {
            grade = "P";
        } else if (average < 60) {
            grade = "D";
        }
        return grade;
    }


    public void generateReport(String indicator, int[] scores, int mode) {
        if (mode == 1) {
            double average = computeAverage(scores);
            String value = String.format("%.2f", average);
            System.out.println("Report for " + indicator);
            System.out.println("Scores: " + Arrays.toString(scores));
            System.out.println("Average: " + value);
            System.out.println();
        } else if (mode == 2) {
            double median = computeMedian(scores);
            String value = String.format("%.2f", median);
            System.out.println("Report for " + indicator);
            System.out.println("Scores: " + Arrays.toString(scores));
            System.out.println("Median: " + value);
            System.out.println();
        } else if (mode == 3) {
            int max = findHighestScore(scores);
            String value = String.valueOf(max);
            System.out.println("Report for " + indicator);
            System.out.println("Scores: " + Arrays.toString(scores));
            System.out.println("Highest: " + value);
            System.out.println();
        } else if (mode == 4) {
            String grade = letterGrade(scores);
            System.out.println("Report for " + indicator);
            System.out.println("Scores: " + Arrays.toString(scores));
            System.out.println("Letter Grade: " + grade);
            System.out.println();
        } else if (mode == 5) {
            String improve = isImproving(scores);
            System.out.println(improve);
        } else {
            String grade = letterGrade(scores);
            int max = findHighestScore(scores);
            String result = String.valueOf(max);
            double median = computeMedian(scores);
            String value = String.format("%.2f", median);
            double average = computeAverage(scores);
            String result2 = String.format("%.2f", average);
            System.out.println("Report for " + indicator);
            System.out.println("Scores: " + Arrays.toString(scores));
            System.out.println("Average: " + result2);
            System.out.println("Median: " + value);
            System.out.println("Highest: " + result);
            System.out.println("Letter Grade: " + grade);
            System.out.println();
        }
    }


    public String isImproving(int[] scores) {

        return isImprovingHelper(scores, 1, true, true);
    }
    private String isImprovingHelper(int[] scores, int index, boolean nonDecreasingSoFar, boolean nonIncreasingSoFar) {

        if (index == scores.length - 1) {
            if (scores[index] > scores[index - 1]) {
                nonIncreasingSoFar = false;
            } else if (scores[index] < scores[index - 1]) {
                nonDecreasingSoFar = false;
            }
            if (nonDecreasingSoFar == false && nonIncreasingSoFar == false) {
                return "mixed";
            } else if (nonDecreasingSoFar == true) {
                return "increasing";
            } else {
                return "decreasing";
            }
        }
        if (scores[index] > scores[index - 1]) {
            nonIncreasingSoFar = false;
        } else if (scores[index] < scores[index - 1]) {
            nonDecreasingSoFar = false;
        }
        return isImprovingHelper(scores, index + 1, nonDecreasingSoFar, nonIncreasingSoFar);
    }

}
