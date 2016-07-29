import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Emanuele on 28/07/16.
 */
public class MainClass {
    private String result_type = "";
    private int w = 0;
    private  int h = 0;
    private String pwd = "";

    public MainClass(){

    }

    public String findpwd(){
        try {
            Process p = Runtime.getRuntime().exec("pwd");
            InputStream iStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(iStream);
            BufferedReader bufReader = new BufferedReader(inputStreamReader);
            pwd = bufReader.readLine();
            return pwd;
        }
        catch (Exception e){
            System.out.println("Exception in the terminal pwd command " + e);
            return pwd;
        }

    }

    public void miffile(String args){
        try{
            System.out.println("Enter the additional image you want to add to memory and if you want to resize, its new size (width and height) otherwise write 0 0");

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            String[] word = s.split(" ");
            MifAdder oldmif = new MifAdder(args,word[0]);
            LoadImg additionalimg = new LoadImg(pwd + "/" + word[0]);

            int width = Integer.parseInt(word[1]);
            int height = Integer.parseInt(word[2]);
            w = width;
            h = height;
            result_type = additionalimg.getType();
            if ((width != 0) && (height != 0)){
                additionalimg.imageFormat(width,height);
                additionalimg = new LoadImg(pwd + "/" + "resized" + result_type.toUpperCase() + "." + result_type.toLowerCase());
            }
            additionalimg.RGBColor();

            MifGenerator mif = new MifGenerator(pwd + "/", "color-Greyscale.txt");
            String[] words_to_add = mif.getWords(); // array with all numbers to add
            System.out.println("Enter the name the mif file will have (wothout extension):");

            BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
            String s2 = bufferRead2.readLine();
            String[] name = s2.split(" ");
            oldmif.CreateNewFile(words_to_add, name[0]);
        }
        catch(IOException e)
        {
            System.out.println("Wrong or inesistent image or mif file! ");

        }
        catch (ArrayIndexOutOfBoundsException a){
            if (a.getMessage().equals("Message")){
                System.out.println("Not enough memory to hold all the images!");
            }
            else{
                System.out.println("Add also image size or 0 0");
            }

        }
        catch(Exception q){
            System.out.println("No such image found! Remember to add also the extension");

        }
    }
    public String getResult(){
        return result_type;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }
    public void imagefile(String[] args){
        try {

            int width = Integer.parseInt(args[1]);
            int height = Integer.parseInt(args[2]);
            w = width;
            h = height;
            int word = Integer.parseInt(args[3]);
            int q_out = Integer.parseInt(args[4]);
            LoadImg img = new LoadImg(pwd + "/" + args[0]);
            result_type = img.getType();
            if ((width != 0) && (height != 0)) {
                img.imageFormat(width, height);
                img = new LoadImg(pwd + "/" + "resized" + result_type.toUpperCase() + "." + result_type.toLowerCase());
            } else {
                width = img.getWidth();
                height = img.getHeight();
            }
            img.RGBColor();
            int mem_used = width * height;
            if (word > 65536) {
                throw new ExceptionInInitializerError();
            }
            if (word == 0) {

                if (mem_used > 65536) {
                    throw new ExceptionInInitializerError();
                } else if (mem_used > 32768) {
                    word = 65536;
                } else if (mem_used > 16384) {
                    word = 32768;
                } else if (mem_used > 8192) {
                    word = 16384;
                } else if (mem_used > 4096) {
                    word = 8192;
                } else if (mem_used > 2048) {
                    word = 4096;
                } else if (mem_used > 1024) {
                    word = 2048;
                } else if (mem_used > 512) {
                    word = 1024;
                } else if (mem_used > 256) {
                    word = 512;
                } else if (mem_used > 128) {
                    word = 256;
                } else if (mem_used > 64) {
                    word = 128;
                } else if (mem_used > 32) {
                    word = 64;
                } else {
                    word = 32;
                }
                System.out.println("Memory size of " + word + "words. Insert it in Altera Megawizard.");
            }
            if (q_out == 0){
                q_out = 8;
            }
            MifGenerator mif = new MifGenerator(pwd + "/", "color-Greyscale.txt", word, q_out, mem_used);
            mif.Writing(args[0]);
        }
        catch(IOException a){
            System.out.println("Image or mif file not found!");
        }
        catch(ExceptionInInitializerError mem){
            if (mem.getMessage().equals("Extension")){
                System.out.println("Add also image or .mif extension");
            }
            System.out.println("Too much memory required! Please riconsider resizing the image.");
        }
        catch(IllegalArgumentException f ) {

            if (f instanceof NumberFormatException){
                System.out.println("Please insert valid numbers for memory used");
            }
            else{
                System.out.println("Please insert a valid image file with its extension in the current folder");
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please write the image or mif file name and extension. If it's an image, add image dimension, memory required and word size");
            System.out.println(" ");
            System.out.println("For example java MifGenerator image_in_this_folder.jpg 160 120 32768 8");
            System.out.println(" ");
            System.out.println("This will generate from image_in_this_folder.jpg  a mif file for an image of 160 x 120 for a rom/ram of 32768 words of 8 bit.");
            System.out.println(" ");
            System.out.println("For more info see README.txt");
        }
    }

}
