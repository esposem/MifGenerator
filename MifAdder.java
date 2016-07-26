import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class MifAdder {

    private ArrayList<String> oldword;
    private int MEM_SIZE;
    private int oldmifsize;

    public MifAdder(String path, String imagename) throws IOException {
        oldword = new ArrayList<>();
        String lastaddress = "";
        Scanner sc = new Scanner(new File(path));
        boolean stopSearchingDepth = false;
        while (sc.hasNext() ) {
            String line = sc.nextLine();

            if (line.replaceAll("[\u0000-\u001f]", "").equals("--finished")){
                break;
            }
            else
            {
                lastaddress = line.split(" ")[0];
                if (!(stopSearchingDepth))
                {
                    String[] l = line.split(" ");
                    for (int i = 0; i < l.length; i++)
                    {
                        if (l[i].equals("DEPTH"))
                        {
                            MEM_SIZE = Integer.parseInt(l[i+2].replaceAll(";",""));
                            stopSearchingDepth = true;
                        }

                    }
                }
                if ((line.length() > 14) && (line.substring(0,13).equals("-- Image used"))){
                    line += " " + imagename;
                }
                    oldword.add(line);

            }
        }
        if (!(lastaddress.equals("END;"))){
            oldmifsize = Integer.parseInt(lastaddress);
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Message");
        }

    }


    public void CreateNewFile(String[] toAdd, String name) throws ArrayIndexOutOfBoundsException{
        try{

            PrintWriter writer;
            writer = new PrintWriter(name+ ".mif", "UTF-8");
            int j = 0;
            while (!(oldword.get(j).equals("BEGIN"))){
                writer.println(oldword.get(j));
                j++;
            }

            writer.println("BEGIN");
            for (String line: oldword.subList(j+ 1,oldword.size())){ //maybe index too large
                writer.println(line);

            }
            if (oldmifsize + toAdd.length < MEM_SIZE){

                writer.println("--additional image");
                int k = 0;
                while(k < toAdd.length){
                    oldmifsize++;
                    writer.println("" + oldmifsize + " : " + toAdd[k] + ";");
                    k ++;
                }
                if (oldmifsize < MEM_SIZE) {
                  writer.println("--finished");
                    oldmifsize++;
                    writer.println("[" + oldmifsize + ".." + (MEM_SIZE - 1) + "] : " + 0 + ";");
                    System.out.println("You still have "+ (MEM_SIZE - oldmifsize) + " free words!");
                }
                writer.println("");
                writer.println("END;");
                writer.close();
            }
            else {
                throw new ArrayIndexOutOfBoundsException("Message");
            }



    }
        catch(IOException e){
        System.out.println("Error creating the mif file ");
        System.out.println("Error : " + e);
    }
    }

}
