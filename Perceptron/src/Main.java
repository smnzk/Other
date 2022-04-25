import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // > 0 versicolor
        // <= 0 setosa

        double learningRate = 0.2;
        File file = new File("src/IrisTraining.txt");
        Scanner scanner = new Scanner(file);
        String[] ar = scanner.nextLine().split(",");
        ArrayList<Double> init = new ArrayList<>();
        for(int i = 0; i < ar.length; i++) {
            if(i % 2 == 0) {
                init.add(1.0);
            } else {
                init.add(0.5);
            }
        }


        File file1 = new File("src/IrisTraining.txt");
        Scanner scanner1 = new Scanner(file1);

        ArrayList<Thing> trainingThings = new ArrayList<>();

        while (scanner1.hasNextLine()) {

            String[] words = scanner1.nextLine().split(",");

            ArrayList<Double> atrs = new ArrayList<>();

            for(int i = 0; i < words.length - 1; i++) {
                atrs.add(Double.parseDouble(words[i]));
            }

            trainingThings.add(new Thing(1.0, atrs, words[words.length - 1]));

        }


        File file2 = new File("src/IrisTest.txt");
        Scanner scanner2 = new Scanner(file2);

        ArrayList<Thing> testThings = new ArrayList<>();

        while (scanner2.hasNextLine()) {

            String[] words = scanner2.nextLine().split(",");

            ArrayList<Double> atrs = new ArrayList<>();

            for(int i = 0; i < words.length - 1; i++) {
                atrs.add(Double.parseDouble(words[i]));
            }

            testThings.add(new Thing(1.0 ,atrs, words[words.length - 1]));

        }


        Weight weight1 = new Weight(init);
        System.out.println(weight1);

        for(int i = 0; i < 30; i++) {
            for (Thing t : trainingThings) {

                if (calculatePlacement(t, weight1) > 0 && t.type.equals("Iris-versicolor")) {
                    continue;
                } else if (calculatePlacement(t, weight1) > 0 && t.type.equals("Iris-setosa")) {
                    ArrayList<Double> temp = new ArrayList<>();
                    temp.add(correctWeight(weight1.atrs.get(0), learningRate, -1.0, t.first));
                    for(int k = 1; k < weight1.atrs.size(); k++) {
                        temp.add(correctWeight(weight1.atrs.get(k), learningRate, -1.0, t.atrs.get(k-1)));
                    }
                    weight1 = new Weight(temp);
                } else if (calculatePlacement(t, weight1) <= 0 && t.type.equals("Iris-setosa")) {
                    continue;
                } else if (calculatePlacement(t, weight1) <= 0 && t.type.equals("Iris-versicolor")) {
                    ArrayList<Double> temp = new ArrayList<>();
                    temp.add(correctWeight(weight1.atrs.get(0), learningRate, 1.0, t.first));
                    for(int k = 1; k < weight1.atrs.size(); k++) {
                        temp.add(correctWeight(weight1.atrs.get(k), learningRate, 1.0, t.atrs.get(k-1)));
                    }
                    weight1 = new Weight(temp);
                }
            }
        }


        System.out.println(weight1);

        int correct = 0;
        int total = 0;
        for(Thing t : testThings) {
            if (calculatePlacement(t, weight1) > 0 && t.type.equals("Iris-versicolor")) {
                correct++;
            } else if (calculatePlacement(t, weight1) <= 0 && t.type.equals("Iris-setosa")) {
                correct++;
            }
            total++;
        }

        System.out.println("correct: " + correct);
        System.out.println("total: " + total);

        while (true) {
            Scanner scanner3 = new Scanner(System.in);
            System.out.println("*************");
            System.out.println("Wprowadź własny obiekt");

            String[] words = scanner3.nextLine().split(",");
            ArrayList<Double> atrs = new ArrayList<>();

            for (int i = 0; i < words.length - 1; i++) {
                atrs.add(Double.parseDouble(words[i]));
            }

            Thing userThing = new Thing(1.0, atrs, words[words.length - 1]);

            if (calculatePlacement(userThing, weight1) > 0) {
                System.out.println("To Iris-versicolor");
            } else {
                System.out.println("To Iris-setosa");
            }
        }

    }

    public static double calculatePlacement(Thing t, Weight w) {

        double result = 0;

        result = t.first * w.atrs.get(0);

        for (int i = 1; i < t.atrs.size(); i++) {
            result = result + t.atrs.get(i - 1) * w.atrs.get(i);
        }

        return result;

    }

    public static double correctWeight(double weight, double learningRate,
                                       double placement, double higherOrLower) {
        double correctedWeight = weight + learningRate*higherOrLower*placement;

        return correctedWeight;
    }

}
