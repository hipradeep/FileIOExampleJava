import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Processing {

    Map<String, String> wordSubstitutesMaps = new HashMap<>();


    public Processing() {
        this.wordSubstitutesMaps = ReadExcelFile.readExcelFile();
    }


    public void startProcessing(String inputDir, String outputDir, int type) {
        File unprocessedFiles = new File(inputDir);
        File[] unprocessedFilesList = unprocessedFiles.listFiles();
        assert unprocessedFilesList != null;
        if (unprocessedFilesList.length != 0) {
            for (File inputFile : unprocessedFilesList) {
                if (inputFile.exists()) {
                    //print file details
                    printFileNameAndSize(inputFile);
                    //process file
                    processFile(inputFile, outputDir, type);
                } else {
                    System.out.println("File DOES NOT EXIST! : " + inputFile);
                }
            }
        } else {
            System.out.println("***File not exist!***\n");
        }

    }

    public void startProcessingUsingThread(String inputDir, String outputDir, int type) {

        File unprocessedFiles = new File(inputDir);
        File[] unprocessedFilesList = unprocessedFiles.listFiles();
        assert unprocessedFilesList != null;
        if (unprocessedFilesList.length != 0) {
            for (File inputFile : unprocessedFilesList) {
                if (inputFile.exists()) {
                    //print file details
                    printFileNameAndSize(inputFile);

                    //creating thread, for each process
                    Thread processThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //process file
                            processFile(inputFile, outputDir, type);
                        }
                    });
                    processThread.start();

                } else {
                    System.out.println("File DOES NOT EXIST! : " + inputFile);
                }
            }
        } else {
            System.out.println("***File not exist!***\n");
        }

    }

    private void processFile(File inputFile, String outputDir, int type) {

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            //
            fileReader = new FileReader(inputFile.getAbsolutePath());
            //
            fileWriter = new FileWriter(outputDir + inputFile.getName());
            Scanner sc = new Scanner(fileReader);
            StringBuilder buffer = new StringBuilder();

            while (sc.hasNext()) {

                String line = sc.nextLine();

                String[] words = line.split(" ");

                for (int i = 0; i < words.length; i++) {

                    String s = replaceWord(words[i], type);
                    buffer.append(s);
                    if (i != words.length - 1) buffer.append(" ");
                }
                buffer.append("\n");
            }

            fileWriter.write(buffer.toString());
            fileWriter.flush();
        } catch (Exception e) {
            //printing error
            System.out.println(e.getMessage());
        } finally {
            //closing the file
            try {
                if (fileReader != null) fileReader.close();
                if (fileWriter != null) fileWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void cleanDirectories(String dir) {
        File files = new File(dir);
        File[] unprocessedFilesList = files.listFiles();
        assert unprocessedFilesList != null;
        for (File file : unprocessedFilesList) {
            if (!file.isDirectory()) file.delete();
        }


    }

    private void printFileNameAndSize(File inputFile) {
        //printing filename
        System.out.println("File : " + inputFile.getName());
        // Displaying whether the file is readable or not, and size of file in KB
        System.out.println("Readable : " + inputFile.canRead() + " | File Size : " + AppConstants.fileSizeKb(inputFile));
    }


    //replace words with its substitutes
    private String replaceWord(String word, int type) {
        //getting substitutes, of perspective word, if exits
        if (type == AppConstants.PROCESSE) return wordSubstitutesMaps.getOrDefault(word, word);
        else {
            for (Map.Entry<String, String> entry : wordSubstitutesMaps.entrySet()) {
                if (entry.getValue().equals(word)) {
                    return entry.getKey();
                }
            }
            return word;
        }
    }


}
