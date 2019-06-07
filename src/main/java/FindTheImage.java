
import org.apache.commons.io.FileUtils;
import sun.misc.IOUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class FindTheImage {

        List<File> filing;


        public List<String> files=new ArrayList();

        public List<File> findAFile(String collectionName, String fn){
            List<File> stuff=new ArrayList<File>();
            List<File> noImages = new ArrayList<File>();
            String endOfString= fn;
            File[] files = new File("/Users/markrowe/austin/javaproject/rugsales/current/" + collectionName).listFiles();
            if(files==null){
                files = new File("/Users/markrowe/austin/javaproject/rugsales/Discontinued/" + collectionName).listFiles();
                if(files==null) {
                    System.out.println("******************************************There is not file for " );
                    System.out.println("******************************************There is not file for " + collectionName + " and " + fn);
                    System.out.println("******************************************There is not file for " );
                    File fakeImage = new File("/Users/markrowe/austin/javaproject/rugsales/noimage.jpg");
                    noImages.add(fakeImage);
                    stuff = noImages;
                }else{
                    stuff = getFile(files, endOfString);
                }
            }else {
                stuff = getFile(files, endOfString);
            }
            System.out.println("hello");
            return stuff;
        }

        public List<File> getFile(File[] files,String name) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFile(file.listFiles(),name);
                } else {
                    if(file.getName().contains(name) && file.getName().endsWith(".jpg")){
                        System.out.println("this File: " + file.getAbsoluteFile());
                        filing.add(file);
                    }else{
                        System.out.println("Not this one:" + file.getAbsoluteFile());
                    }
                }
            }
            return filing;
        }

        public List<String> AnotherAttempt(File current, File future, File source) {
            System.out.println(current.getAbsoluteFile());

            List<String> lines = new ArrayList<String>();
            FileReader what;
            try {
                what = new FileReader(source.getAbsoluteFile());
//                InputStream is =
//                        JsonParsing.class.getResourceAsStream( "sample-json.txt");
//                String jsonTxt = IOUtils.toString( is );
//
//                JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );
//                double coolness = json.getDouble( "coolness" );
//                int altitude = json.getInt( "altitude" );

                BufferedReader br = new BufferedReader(what);


                String st;
                while ((st = br.readLine()) != null){
                    st=st.replaceAll("\"","");
                    st=st.replaceFirst(" ","");
              //      st=st.replaceAll("[","");
              //      st=st.replaceAll("]","");
                    lines.add(st);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //test
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lines;
        }


        public List<File> moveFiles(List<String> list) {
            for (String filename : list) {
                if(filename.length()>4) {
                    File source = new File("/Users/markrowe/austin/linker" + filename.trim());
                    File dest = new File("/Users/markrowe/austin/trimdown" + filename.trim());
                    try {
                        FileUtils.copyDirectory(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return filing;
        }

        public static void main(String[] args) {
            System.out.println("hello");
            FindTheImage fit = new FindTheImage();
            File current = new File("/Users/markrowe/austin/trimdown");
            File future = new File("/Users/markrowe/austin/linker");
            File source = new File("/Users/markrowe/IdeaProjects/rugimage");
            source = new File("/Users/markrowe/IdeaProjects/rugimage/picURLlist.json");
            List<String> list=fit.AnotherAttempt(current,future,source);
            //   System.out.println(fit.moveTheFilesArround() + "images loaded");
            fit.moveFiles(list);
            System.out.println("done");


            //      fit.listSourceFiles(path.getRoot());

        }
    }
