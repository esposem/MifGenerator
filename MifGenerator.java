// Created by Emanuele Giuseppe Esposito
import java.io.*;
import java.util.Scanner;

// Principal Class. Here there is the main. MifGenerator creates the .mif file.

public class MifGenerator {
    private String[] words;
    private static int MEMORY_SIZE;// = 32768;
    private  String filename;
    private  int width;

    public MifGenerator(String path, String filename) {
        try {
            readLargerTextFile(path + filename);
        }
        catch(IOException e){
            System.out.println("Exception in reading the file");
        }

    }
    public MifGenerator(String path, String filename, int memoryrequired, int width, int memoryused) {
        words = new String[memoryused]; //19200
        this.filename = filename;
        this.width = width;
        MEMORY_SIZE = memoryrequired;
        try {
            readLargerTextFile(path + filename);
        }
        catch(IOException e){
            System.out.println("Exception in reading the file");
        }

    }

    public void readLargerTextFile(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNext()){
            String line = sc.nextLine();
            words = line.split(" ");
        }
    }
    public String[] getWords(){
        return words;
    }

    public void Writing(String file) {
        try {
            if (words.length == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (words.length > MEMORY_SIZE) {
                throw new ArrayIndexOutOfBoundsException("Too Large");
            }
            int counter = 0;
            PrintWriter writer;
            writer = new PrintWriter((file.substring(0,file.length() - 5)) + ".mif", "UTF-8");
            writer.println("-- Image used: " + file);
            writer.println("DEPTH = " + MEMORY_SIZE + ";");
            writer.println("WIDTH = " + width + ";");
            writer.println("ADDRESS_RADIX = UNS;");
            writer.println("DATA_RADIX = UNS;");
            writer.println("CONTENT");
            writer.println("BEGIN");
            for (int i = 0; i < words.length; i++){
                writer.println(""+ counter + " : " + words[i] + ";");
                counter ++;
            }
            if (counter < MEMORY_SIZE){
                writer.println("--finished");
                writer.println("["+ counter + ".." + (MEMORY_SIZE -1) + "] : " + 0 + ";");
            }
            writer.println("");
            writer.println("END;");
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error creating the mif file ");
            System.out.println("Error : " + e);
        }
        catch (ArrayIndexOutOfBoundsException a){
            if (a.getMessage().equals("Too Large")){
                System.out.println("Not enough memory specified.");
                System.out.println("Process stopped");
            }
            else {
                System.out.println("File  " + filename + " empty");
            }
        }
    }



    public static void main(String[] args)
    {
        // TODO: support for non-greyscale

        MainClass m = new MainClass();
        String result_type = "";
        int w = 0;
        int h = 0;
        String pwd = m.findpwd();
        try {
            if ((args[0].length() > 3) && (args[0].substring(args[0].length() -4, args[0].length() ).equals(".mif"))){
                m.miffile(args[0]);
            }
            else {
                m.imagefile(args);
            }
        }
        catch(ArrayIndexOutOfBoundsException a)
        {
            System.out.println(" You must insert an image or a mif file");
        }
        catch(StringIndexOutOfBoundsException l){
            if (args[0].charAt(0) == '-') {
                System.out.println("The input parameter must be:");
                System.out.println(" ");
                System.out.println("Image with extension a valid image of any readable format (png, jpg, jpeg)");
                System.out.println(" ");
                System.out.println("How many words you need, size of the orm/ram (if unknown, write 0 and insert the resulting number in Altera Megawizard)");
                System.out.println(" ");
                System.out.println("Image size (width height) of the resulting mif file (if you want to discard write 0 0), if you want to resize the image before processing it.");
                System.out.println(" ");
                System.out.println("q-output, size of each word. If you want default, write 0");
            }
        }
        finally {
            result_type = m.getResult();
            w = m.getW();
            h = m.getH();
            try {
                if(!(result_type.equals(""))) {
                    Runtime.getRuntime().exec("rm color-Greyscale.txt");
                    if ((w != 0) && (h != 0)){
                        System.out.println("Do you want to delete the resized image?");
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String s = bufferRead.readLine();
                        String[] word = s.split(" ");
                        if(word[0].toLowerCase().startsWith("y")) {
                            System.out.println("Deleted.");
                            Runtime.getRuntime().exec("rm resized" + result_type.toUpperCase() + "." + result_type.toLowerCase());
                        }
                    }
                }
            }
            catch (IOException k){
                System.out.println("Exception in deleting color-Greyscale.txt  or the resized image. Please delete it yourself ");
            }
            System.out.println("Process finished.");
        }
    }


}
