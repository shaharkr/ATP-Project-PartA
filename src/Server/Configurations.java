package Server;

import java.io.*;
import java.util.Properties;

/**
 * config file meant to help the the server\client decide which algorithms to use and
 * how many threads can work simultaneously.
 * it is a singleton to prevent more than one creation.
 */
public class Configurations {
    private static Configurations config_inst=null;
    private Properties p;
    static Configurations getInstance(){
        if(config_inst==null)
            return new Configurations();
        return config_inst;

    }

    /**
     * Constructor. sets default values to the thread pool size, the generating algo and the search algo.
     */
    private Configurations() {
        try {
            OutputStream os = new FileOutputStream("resources\\config.properties");
            this.p = new Properties();
            this.p.setProperty("threadPoolSize","2");
            this.p.setProperty("mazeGeneratingAlgorithm","MyMazeGenerator");
            this.p.setProperty("mazeSearchingAlgorithm","BreadthFirstSearch");
            this.p.store(os,null);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //setting getters
    public int getThreadPoolSize(){
        try {
            InputStream is = new FileInputStream("resources\\config.properties");
            this.p.load(is);
            is.close();
            return Integer.parseInt(this.p.getProperty("threadPoolSize"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getMazeGeneratingAlgorithm(){
        try {
            InputStream is = new FileInputStream("resources\\config.properties");
            this.p.load(is);
            is.close();
            return this.p.getProperty("mazeGeneratingAlgorithm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMazeSearchingAlgorithm(){
        try {
            InputStream is = new FileInputStream("resources\\config.properties");
            this.p.load(is);
            is.close();
            return this.p.getProperty("mazeSearchingAlgorithm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //setting setters.
    public void setThreadPoolSize(String val){
        try {
            int num = Integer.parseInt(val);
            if(num<=0) throw new Exception();
        } catch (Exception e) {
            System.out.println("Input String must be positive integer");
        }
        try {
            OutputStream os = new FileOutputStream("resources\\config.properties");
            this.p.setProperty("threadPoolSize",val);
            this.p.store(os,null);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setMazeGeneratingAlgorithm(String val){
        if(!val.equals("MyMazeGenerator") && !val.equals("SimpleMazeGenerator") && !val.equals("EmptyMazeGenerator"))
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Not a valid generating algorithm");
            }
        try {
            OutputStream os = new FileOutputStream("resources\\config.properties");
            this.p.setProperty("mazeGeneratingAlgorithm",val);
            this.p.store(os,null);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setMazeSearchingAlgorithm(String val){
        try {
            if(!val.equals("BreadthFirstSearch") && !val.equals("DepthFirstSearch") && !val.equals("BestFirstSearch"))
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Not a valid searching algorithm");
                }
            OutputStream os = new FileOutputStream("resources\\config.properties");
            this.p.setProperty("mazeSearchingAlgorithm",null);
            this.p.store(os, null);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
