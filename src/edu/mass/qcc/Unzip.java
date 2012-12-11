/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

/**
 *
 * @author Chris
 * @author Em
 * @unzips the Webspec files to the Webspec Directory
 */
import java.io.*;
import java.util.*;
import java.util.zip.*;


public class Unzip {
    private static String path;
    private static String fn;
 
  /**
   * Unzips a file for use by the program.
   * @author Chris
   * @author Em
   * @param zipFile
   * @param jiniHomeParentDir
   */
  public static void unzip(ZipFile zipFile, File jiniHomeParentDir) {
    
    Enumeration files = zipFile.entries();
    File f = null;
    FileOutputStream fos = null;
    
    while (files.hasMoreElements()) {
      try {
        ZipEntry entry = (ZipEntry) files.nextElement();
        InputStream eis = zipFile.getInputStream(entry);
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
  
        f = new File(jiniHomeParentDir.getAbsolutePath() + File.separator + entry.getName());
        
        if (entry.isDirectory()) {
            System.out.println("Creating DIR:"+ entry.getName());
          f.mkdirs();
          continue;
        } else {
          f.getParentFile().mkdirs();
          f.createNewFile();
        }
        
        fos = new FileOutputStream(f);
  
        while ((bytesRead = eis.read(buffer)) != -1) {
          fos.write(buffer, 0, bytesRead);
        }
      } catch (IOException e) {
        e.printStackTrace();
        continue;
      } finally {
        if (fos != null) {
          try {
            fos.close();
          } catch (IOException e) {
            
          }
        }
      }
    }
  }

}