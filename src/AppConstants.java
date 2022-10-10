import java.io.File;

public class AppConstants {
    public static final String UNPROCESSED_FILE_DIR="F:\\IdeaProjects\\HeadStraitAssignment\\Unprocessed\\";
    public static final String PROCESSED_FILE_DIR="F:\\IdeaProjects\\HeadStraitAssignment\\Processed\\";
    public static final String REPROCESSED_FILE_DIR="F:\\IdeaProjects\\HeadStraitAssignment\\Reprocessed\\";

    public static final String RESOURCE_FILE_DIR="F:\\IdeaProjects\\HeadStraitAssignment\\Resources\\";

    public static  final int PROCESSE=100;
    public static  final int REPROCESSE=101;

    public static String fileSizeKb(File file) {
        long sizeInByte = file.length();
        return String.format("%.2f", sizeInByte / 1024f) + "KB";

    }
}
