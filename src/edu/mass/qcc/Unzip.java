/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;

/**
 *
 * @author Chris
 * @author Em
 * @unzips the Webspec file to the Webspec Directory
 */
import java.io.*;
import java.util.*;
import java.util.zip.*;


public class Unzip {
    Unzip(){}

  public void copyInputStream(InputStream in, OutputStream out)
  throws IOException
  {
      System.out.println("copying inputstream");
    byte[] buffer = new byte[1024];
    int len;

    while((len = in.read(buffer)) >= 0)
      out.write(buffer, 0, len);

    in.close();
    out.close();
  }

  public void myunzip(String fname) {
    Enumeration entries;
    ZipFile zipFile;

    if(fname.length() != 1) {
      System.err.println("Usage: Unzip zipfile");
      return;
    }

    try {
      zipFile = new ZipFile(fname);

      entries = zipFile.entries();

      while(entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry)entries.nextElement();

        if(entry.isDirectory()) {
          // Assume directories are stored parents first then children.
          System.err.println("Extracting directory: " + entry.getName());
          // This is not robust, just for demonstration purposes.
          (new File(entry.getName())).mkdir();
          continue;
        }

        System.err.println("Extracting file: " + entry.getName());
        copyInputStream(zipFile.getInputStream(entry),
           new BufferedOutputStream(new FileOutputStream(entry.getName())));
      }

      zipFile.close();
    } catch (IOException ioe) {
      System.err.println("Unhandled exception:");
      ioe.printStackTrace();
      return;
    }
  }

}
