package com.company.network;

import com.company.Vertex;
import com.company.algorithm.AlgorithmFactory;
import com.company.algorithm.MyAlgorithm;
import com.sun.org.apache.regexp.internal.RE;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkParser {

    public static final int REQUIRED = 1;
    public static final int WITHOUT_REQUIRED = 0;

    private static final int START_INDEX_VERTEX = 8;
    private static final int START_INDEX_LINK = 8;
    private static final int START_INDEX_ALGORITHM = 11;
    private static final int NUMBERS_PER_LINE_LINK = 3;
    private static final int NUMBERS_PER_LINE_VERTEX = 3;

    private Stage fileChooserStage;
    private MyAlgorithm algorithm;

    public NetworkParser(Stage fileChooserStage) {
        this.fileChooserStage = fileChooserStage;
    }

    public Network parseNetwork(int direction, int required) throws NullPointerException {

        File file = openFileChooserAndChooseAFile();

        if(file == null) {
            throw new NullPointerException();
        } else {

            Network network = parseFile(file, direction, required);

            if(network == null || algorithm == null) {
                throw new NullPointerException();
            } else {

                algorithm.execute(network);

                return network;
            }
        }
    }

    private Network parseFile(File file, int direction, int requiredLinks) {

        try {

            Scanner input = new Scanner(file);
            NetworkFactory networkFactory = new NetworkFactory();
            Network network = networkFactory.produceNetwork(direction);

            ArrayList<Vertex> vertices;

            vertices = parseVertices(input, network, requiredLinks);

            parseLinks(input, network, vertices);

            algorithm = parseTypeOfAlgorithm(input);
            algorithm.setInputPaths(parseInputPaths(input));

            input.close();

            return network;

        } catch (Exception ex) {

            ex.printStackTrace();

            return null;
        }
    }

    private ArrayList<Vertex> parseVertices(Scanner input, Network network, int includesRequired) {

        ArrayList<Vertex> vertices = new ArrayList<>();
        int vertexAmount = parseAmountOf(input, START_INDEX_VERTEX);

        String line;
        String[] numbers;
        int id, required, i = 0;
        double x, y;

        while(i < vertexAmount) {

            line = input.nextLine();

            if(notComment(line)) {

                switch(includesRequired) {
                    case WITHOUT_REQUIRED:
                        numbers = line.split(" ", NUMBERS_PER_LINE_VERTEX);

                        id = Integer.parseInt(numbers[0]);
                        x = Double.parseDouble(numbers[1]);
                        y = Double.parseDouble(numbers[2]);
                        vertices.add(new Vertex(id, x, y));
                        break;
                    case REQUIRED:
                        numbers = line.split(" ", 4);

                        id = Integer.parseInt(numbers[0]);
                        x = Double.parseDouble(numbers[1]);
                        y = Double.parseDouble(numbers[2]);
                        required = Integer.parseInt(numbers[3]);
                        vertices.add(new Vertex(id, x, y, (required == 1)));
                        break;
                }

                i++;
            }
        }

        addAllParsedVertices(network, vertices);

        return vertices;
    }

    private void parseLinks(Scanner input, Network network, ArrayList<Vertex> vertices) {

        int linksAmount = parseAmountOf(input, START_INDEX_LINK);
        int id, start, end, i=0;
        String line;

        while(i < linksAmount) {

            line = input.nextLine();

            if(notComment(line)) {

                String[] numbers = line.split(" ", NUMBERS_PER_LINE_LINK);

                id = Integer.parseInt(numbers[0]);
                start = Integer.parseInt(numbers[1]);
                end = Integer.parseInt(numbers[2]);

                network.addLink(id, vertices.get(start-1), vertices.get(end-1));
                i++;
            }
        }

    }

    private MyAlgorithm parseTypeOfAlgorithm(Scanner input) {

        AlgorithmFactory factory = new AlgorithmFactory();
        String line;

        while (input.hasNextLine()) {

            line = input.nextLine();

            if(notComment(line)) {

                String alg = line.substring(START_INDEX_ALGORITHM, line.length());

                return factory.produceAlgorithm(alg);
            }
        }

        return null;
    }

    private int parseAmountOf(Scanner input, int start) {

        int amount;
        String line;

        while (input.hasNextLine()) {

            line = input.nextLine();

            if(notComment(line)) {

                line = line.substring(start, line.length());
                amount = Integer.parseInt(line);

                return amount;
            }
        }

        return 0;
    }

    private void addAllParsedVertices(Network network, ArrayList<Vertex> vertices) {
        vertices.forEach(v->network.addVertex(v, true));
    }

    private ArrayList<MyAlgorithm.InputPath> parseInputPaths(Scanner input) {

        String line;
        int start, end;
        ArrayList<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();

        while(input.hasNextLine()) {

            line = input.nextLine();

            if(notComment(line)) {

                String[] numbers = line.split(" ", 2);

                start = Integer.parseInt(numbers[0]);
                end = Integer.parseInt(numbers[1]);
                inputPaths.add(new MyAlgorithm.InputPath(start, end));

            }
        }

        return inputPaths;
    }

    private boolean notComment(String line) {
        return !(line.substring(0, 1).matches("#"));
    }

    private File openFileChooserAndChooseAFile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open network file...");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files", "*.txt")
        );

        File file = fileChooser.showOpenDialog(fileChooserStage);

        if (file != null) {
            System.out.println("File: " + file.getAbsolutePath());
            return file;
        } else {
            System.out.println("File invalid or action aborted. Stopped app.");
            return null;
        }

    }

}
