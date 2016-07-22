

import javax.imageio.IIOException;
import java.io.*;
import java.util.Scanner;

public class MifGenerator {
    private String[] words;
    private static int MEMORY_SIZE;// = 32768;
    private  String filename;
    private  int width;

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

    public void Writing(String file) {
        try {
            if (words.length == 0){
                throw new ArrayIndexOutOfBoundsException();
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
            System.out.println("File  " + filename + "empty");
        }
    }


    public static void main(String[] args)
    {
        String pwd = "";
        try {
            Process p = Runtime.getRuntime().exec("pwd");
            InputStream iStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(iStream);
            BufferedReader bufReader = new BufferedReader(inputStreamReader);
            pwd = bufReader.readLine();


        }
        catch (Exception e){
            System.out.println("Exception in the terminal pwd command " + e);
        }

        try {
            int width = Integer.parseInt(args[1]);
            int height = Integer.parseInt(args[2]);
            int word = Integer.parseInt(args[3]);
            int q_out = Integer.parseInt(args[4]);
            LoadImg img = new LoadImg(pwd + "/" + args[0]);
            String result_type = img.getType();
            if ((width != 0) && (height != 0)){
                img.imageFormat(width, height);
                img = new LoadImg(pwd + "/" + "resized" + result_type.toUpperCase() + "." + result_type.toLowerCase());
            }
            else{
                width = img.getWidth();
                height = img.getHeight();
            }
            img.RGBColor();
            int mem_used = width * height;
            if (word == 0){

                if (mem_used > 65536){
                    throw new ExceptionInInitializerError();
                }
                else if (mem_used > 32768){
                    word = 65536;
                }
                else if (mem_used > 16384){
                    word = 32768;
                }
                else if (mem_used > 8192){
                    word = 16384;
                }
                else if (mem_used > 4096){
                    word = 8192;
                }
                else if (mem_used > 2048){
                    word = 4096;
                }
                else if (mem_used > 1024){
                    word = 2048;
                }
                else if (mem_used > 512){
                    word = 1024;
                }
                else if (mem_used > 256){
                    word = 512;
                }
                else if (mem_used > 128){
                    word = 256;
                }
                else if (mem_used > 64){
                    word = 128;
                }
                else if (mem_used > 32){
                    word = 64;
                }
                else{
                    word = 32;
                }
                System.out.println("Memory size of " + word + "words. Insert it in Altera Megawizard.");
            }

            MifGenerator mif = new MifGenerator(pwd + "/", "color-Greyscale.txt", word, q_out, mem_used);
            mif.Writing(args[0]);

            Runtime.getRuntime().exec("rm color-Greyscale.txt");
            Runtime.getRuntime().exec("rm resized" + result_type.toUpperCase() + "." + result_type.toLowerCase());
        }
        catch(ExceptionInInitializerError mem){
            System.out.println("Too much memory required! Please riconsider resizing the image.");
        }
        catch(IllegalArgumentException f){
            if (args[0].startsWith("-")) {
                System.out.println("The input parameter must be:");
                System.out.println(" ");
                System.out.println("Image with extension a valid image of any readable format (png, jpg, jpeg)");
                System.out.println(" ");
                System.out.println("How many words you need, size of the orm/ram (if unknown, write 0 and insert the resulting number in Altera Megawizard)");
                System.out.println(" ");
                System.out.println("Image size (width height) of the resulting mif file (if you want to discard write 0 0), if you want to resize the image before processing it.");
                System.out.println(" ");
                System.out.println("q-output, size of each word. ");
            }
            if (f instanceof NumberFormatException){
                System.out.println("Please insert valid numbers for memory used");
            }
            else{
                System.out.println("Please insert a valid image file with its extension in the current folder");
                System.out.println("Error : " + f);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please write the image name and extension plus image dimension, memory required and word size");
            System.out.println(" ");
            System.out.println("For example java MifGenerator image_in_this_folder.jpg 160 120 32768 8");
            System.out.println(" ");
            System.out.println("This will generate from image_in_this_folder.jpg  a mif file for an image of 160 x 120 for a rom/ram of 32768 words of 8 bit.");
            System.out.println(" ");
            System.out.println("For more info see README.txt");
            System.out.println("Error : " + e);
        }

        catch (IOException i){
            System.out.println("Exception in deleting color-Greyscale.txt  or the resized image. Please delete it yourself ");
            System.out.println("Error : " + i);
        }
        finally {
            System.out.println("Process finished.");
        }
    }


}
