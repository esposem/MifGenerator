import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.PrintWriter;

public class LoadImg
{
    private String type;
    private BufferedImage image;
    private int imgtype;

    public LoadImg(String path)
    {
        try
        {
            image = ImageIO.read(new File(path));

            int len = path.length();
            char[] chararray = path.toCharArray();

            if ((chararray[len-4]  == '.')) {
                type = path.substring(len-3);
            }
            else if(chararray[len-5]  == '.') {
                type = path.substring(len-4);
            }
            else {
                throw new Exception();
            }
            imgtype = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
        }
        catch (Exception e)
        {

        }

    }
    public int getWidth(){
        return  image.getWidth();
    }
    public int getHeight(){
        return  image.getHeight();
    }

    public void RGBColor(){
        try {
            PrintWriter writer = new PrintWriter("color-Greyscale.txt", "UTF-8");
            for (int i = 0; i< this.image.getHeight(); i++){
                for (int j = 0; j < this.image.getWidth(); j++){
                    int blue = new Color(this.image.getRGB(j,i)).getBlue();
                    int red = new Color(this.image.getRGB(j,i)).getRed();
                    int green = new Color(this.image.getRGB(j,i)).getGreen();
                    int gray = (blue + red + green)/3;
                    writer.print(gray + " ");
                }
            }

            writer.close();
        }
        catch(IOException e){
            System.out.println("Not an image! " + e);
        }
    }
    public String getType(){
        return type;
    }

    public void imageFormat(int width, int height){

        try {

            BufferedImage resizeImageJpg = resizeImage(image, imgtype, width, height);
            ImageIO.write(resizeImageJpg, type , new File("resized" + type.toUpperCase() + "." + type.toLowerCase()));
        }
        catch (IOException e){
            System.out.println("Exception creating the resized image " + type + " " + e);
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_WIDTH, int IMG_HEIGHT){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }


}
