import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Hello {

    public static void main(String[] args) throws IOException {

        boolean isExit = false;
        do {
            System.out.println("Choose options for File Process type :- ");
            System.out.println("1 : File Process");
            System.out.println("2 : File Reprocess");
            System.out.println("3 : File Reprocess using Thread");
            System.out.println("4 : Reset Directories ");
            System.out.println("5 : Exit");
            System.out.print("Enter option : ");
            int ch = (new Scanner(System.in)).nextInt();

            switch (ch) {
                case 1:
                    printMessage("Start Processing......!");
                    long startTimeForProcess = System.currentTimeMillis();
                    Processing processing = new Processing();
                    processing.startProcessing(AppConstants.UNPROCESSED_FILE_DIR, AppConstants.PROCESSED_FILE_DIR, AppConstants.PROCESSE);
                    printTime(startTimeForProcess);
                    break;
                case 2:
                    printMessage("Start Reprocessing......!");
                    long startTimeForReprocess = System.currentTimeMillis();
                    Processing processing1 = new Processing();
                    processing1.startProcessing(AppConstants.PROCESSED_FILE_DIR, AppConstants.REPROCESSED_FILE_DIR, AppConstants.REPROCESSE);
                    printTime(startTimeForReprocess);
                    break;
                case 3:
                    printMessage("Start Reprocessing......!");
                    long startTimeForReprocessUsingThread = System.currentTimeMillis();
                    Processing processingThread = new Processing();
                    processingThread.startProcessingUsingThread(AppConstants.UNPROCESSED_FILE_DIR, AppConstants.PROCESSED_FILE_DIR, AppConstants.REPROCESSE);
                    printTime(startTimeForReprocessUsingThread);
                    break;
                case 4:
                    printMessage("Reset Directories....!");
                    Processing.cleanDirectories(AppConstants.PROCESSED_FILE_DIR);
                    Processing.cleanDirectories(AppConstants.REPROCESSED_FILE_DIR);
                    break;
                case 5:
                    printMessage("Exiting the program.......!");
                    isExit = true;
                    break;
                default:
                    printMessage("You enter wrong option");
            }

        } while (!isExit);


    }

    private static void printTime(long startTime) {
        System.out.println("------------------------------");
        System.out.println("Time taken : " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("------------------------------");
    }

    private static void printMap(Map<String, String> m) {
        m.forEach((key, value) -> System.out.println(key + " " + value));
    }


    static void printMessage(String msg) {
        System.out.println("------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------");
    }

}
