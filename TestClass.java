// Created by Emanuele Giuseppe Esposito

import java.io.*;

// This is a test class. To run it, write in the terminal java TestClass
// It has its own main method. This class tests the output with some possible
// wrong input parameter, to see if handles all the error correctly.

public class TestClass {
    public void Test1() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            Process proc = rt.exec("java MifGenerator lin.png");
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: IMAGE ONLY =  java MifGenerator lin.png");
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test2() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = ("java MifGenerator lin 9 9 ");
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test8() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = ("java MifGenerator lin 9 9  32000 8");
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test3() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = ("java MifGenerator lin.png ciao 90");
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }

    public void Test9() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = ("java MifGenerator lin.png ciao 90 32000 8");
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test4() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = ("java MifGenerator lin.png 90 ciao");
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test5() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = "java MifGenerator 30 32 00 299 32 ";
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test6() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = "java MifGenerator lin.png 160 120 0 500";
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test7() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = "java MifGenerator lin.png 160 120 cuqi 599";
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test10() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = "java MifGenerator lin.png 160 120 32000 0";
            Process proc = rt.exec(message);
            rt.exec("y");
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }
    public void Test11() {
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        try {
            StreamWrapper error, output;
            String message = "java MifGenerator lin.png 160 120 32000 cioa";
            Process proc = rt.exec(message);
            output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            int exitVal = 0;

            output.start();
            output.join(3000);
            exitVal = proc.waitFor();
            System.out.println("Input: " + message);
            System.out.println("Output: " + output.message );
            System.out.println(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException i){
            i.printStackTrace();
        }
    }


    public StreamWrapper getStreamWrapper(InputStream is, String type){
        return new StreamWrapper(is, type);

    }
    private class StreamWrapper extends Thread {
        InputStream is = null;
        String type = null;
        String message = null;

        public String getMessage() {
            return message;
        }

        StreamWrapper(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer buffer = new StringBuffer();
                String line = null;
                while ( (line = br.readLine()) != null) {
                    buffer.append(line);//.append("\n");
                }
                message = buffer.toString();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }


    // this is where the action is
    public static void main(String[] args) {
        try{
            Runtime.getRuntime().exec("javac MifGenerator.java");
        } catch(Exception e) {}
        Runtime rt = Runtime.getRuntime();
        TestClass rte = new TestClass();
        StreamWrapper error, output;
        System.out.println("Test 1");
        rte.Test1();
        System.out.println("Test 2");
        rte.Test2();
        System.out.println("Test 3");
        rte.Test3();
        System.out.println("Test 4");
        rte.Test4();
        System.out.println("Test 5");
        rte.Test5();
        //System.out.println("Test 6");
        //rte.Test6();
        System.out.println("Test 7");
        rte.Test7();
        System.out.println("Test 8");
        rte.Test8();
        System.out.println("Test 9");
        rte.Test9();
//        System.out.println("Test 10");
//        rte.Test10();
        System.out.println("Test 11");
        rte.Test11();

    }
}
