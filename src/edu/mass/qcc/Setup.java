/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Scanner;
import java.util.zip.ZipFile;
import javax.swing.JOptionPane;

/**
 * @author Chris and Em
 * @param
 * @Setup and install Webspec and settings file
   */
public class Setup {
      public String WebspecDirectory = "C:/webspec";
      public String WebspecPath = "C:\\webspec\\";
      public String WebspecZipFile = "/edu/mass/qcc/res/webspec.zip";
    /**
     * Setup installs needed Directories and files for use by Autoexplorer.
     * @param ax
     * @param _ax
     * @throws FileNotFoundException
     * @throws IOException 
     */
    Setup(AxBrowser ax, autoexplorer _ax) throws FileNotFoundException, IOException{
        AxBrowser Ax = ax;
        autoexplorer au = _ax;
        System.out.println("Looking for WebSpec DIR"); 
        File dirfile = null;
            //Let user know what we are doing.  EM and Chris
            JOptionPane.showMessageDialog(au.browser.getComponent(), "Autoexplorer needs to install some files.");
                    
            System.out.println("Trying to open dir");
            //See if the directories exist. Em and Chris
            if (!new File(WebspecDirectory).exists()){
            dirfile = new File(WebspecDirectory);    
            System.out.println("DIR does not exist, creating...");
                if(dirfile.mkdir()){
                    System.out.println("Created.");
                        //copy file to directory.
                 
                //copy file to directory.
                    FileOutputStream zos = new FileOutputStream("c:/webspec/webspec.zip");
                
                    System.out.println("Opening local watij for reading");
                    
                    InputStream in = getClass().getResourceAsStream(WebspecZipFile);
                    Scanner scanner = new Scanner(in);
                    
                    System.out.println("Opening zip file location for writing.");
                    File wsf = new File(WebspecPath);
                    wsf.setWritable(true);
                    
                    System.out.println("Writing webspec to directory.");
                    byte[] data = new byte[2048];
                    int b = -1;
                    while ((b = in.read(data)) != -1)
                    {
                    zos.write(data, 0, b);
                    }
                    zos.close();
                    in.close();
            
                //Unzip the files Chris and Em
                Unzip unzip = new Unzip(); 
                //trying to unzip
                System.out.println("Unzipping...");
                Runtime.getRuntime().exec("cmd cd C:/webspec");
                ZipFile zf = new ZipFile("webspec.zip");
                File DIR = new File("c:\\webspec\\");
                Unzip.unzip(zf,DIR);
            
            
            }else{
                
                System.out.println("Dir exists, skipping create.");
                
                }
          
        
                    System.out.println("Opening settings for reading:");
                    
                    InputStream in = null;
                try {
                    
                    File sFile = new File("c:\\AE\\settings\\setting.txt");
                    if (!sFile.exists()){
                        System.out.println("Creating Settings File with defaults.");
                        File aFile = new File("c:\\ae\\");
                        
                        File xFile = new File("c:\\ae\\settings\\");
                        aFile.mkdir();
                        xFile.mkdir();
                        sFile.createNewFile();
                    Writer w = new FileWriter(sFile);
                    w.append("Password:password:Homepage:www.google.com\n");
                    w.close();
                    
                    }
                    in = new FileInputStream("c:/ae/settings/setting.txt");
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("File Not Found!");
                }
                    
                    Scanner scanner = new Scanner(in);
                    //Set the default password and homepage. Chris and Em
                    while (scanner.hasNext()){ 
                        
                        String[] Settings = scanner.nextLine().split(":");
                        String password = Settings[1];
                            System.out.println("Default password is: "+password);
                            
                        String homepage = Settings[3];
                            System.out.println("Default homepage is: "+homepage);
                            Ax.setHome(homepage);
                        
                        in.close();
                    
                    }
                    JOptionPane.showMessageDialog(au.browser.getComponent(), "Setup Complete. Please restart the browser. Use default password 'password' to login.");
                    System.exit(0);
    
    
    }
    
    }}
