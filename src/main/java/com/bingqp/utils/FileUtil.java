package com.bingqp.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

  public static File createFile(String doPath, String saveFileName) throws IOException {
    File file = new File(doPath);
    if (!file.exists()) {//如果文件夹不存在
      file.mkdir();//创建文件夹
    }
    File myfile = new File(doPath, saveFileName); //指定文件路径
    myfile.createNewFile(); //创建文件
    return myfile;
  }

  public static BufferedReader readFile(String filePath) throws IOException {
    return readFile(filePath, "UTF-8");
  }

  public static void writeStringToFile(String filePath, List<String> lines) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)),
        "UTF-8"));

      for (String line : lines) {
        bw.write(line);
        bw.newLine();
      }
      bw.close();
    } catch (Exception e) {
      System.err.println("write errors :" + e);
    }
  }

  /**
   * 读取某个文件夹下的所有文件
   */
  public static List<String> readFileNames(String filepath) throws FileNotFoundException, IOException {
    List<String> listFileNames = new ArrayList<String>();
    try {

      File file = new File(filepath);

      if (!file.isDirectory()) {
        throw new FileNotFoundException();
      } else if (file.isDirectory()) {
        String[] filelist = file.list();
        for (int i = 0; i < filelist.length; i++) {
          File readfile = new File(filepath + "\\" + filelist[i]);
          if (!readfile.isDirectory()) {
            System.out.println("path=" + readfile.getPath());
            System.out.println("absolutepath="
              + readfile.getAbsolutePath());
            String[] split = readfile.getName().split("\\\\");
            listFileNames.add(split[split.length-1]);
            System.out.println("name=" +split[split.length-1] );

          } else if (readfile.isDirectory()) {
            readFileNames(filepath + "\\" + filelist[i]);
          }
        }

      }

    } catch (FileNotFoundException e) {
      System.out.println(filepath + " read Exception:" + e.getMessage());
    }
    return listFileNames;
  }


  public static BufferedReader readFile(String filePath, String charsetName) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),
      charsetName));
    return br;
  }

  public static void writeToFile(File file, String data) throws Exception {
    OutputStream fileOut = null;
    try {
      fileOut = new FileOutputStream(file);
      fileOut.write(data.getBytes());
      fileOut.flush();
    } finally {
      if (fileOut != null) {
        fileOut.close();
      }
    }
  }

  public static void writeToFile(String path, String data) throws Exception {
    writeToFile(new File(path), data);
  }

  public static String loadFile(String resourceName) throws Exception {
    InputStream ins = null;
    try {
      ins = new FileInputStream(resourceName);
      return readFrom(ins, "UTF-8");
    } catch (FileNotFoundException e) {
      return null;
    } catch (Exception e) {
      throw new Exception(e);
    } finally {
      if (ins != null) {
        try {
          ins.close();
        } catch (Exception ignored) {
        }
      }
    }
  }

  public static String readFrom(InputStream is, String charsetName) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(16 * 1024);

    copy(is, baos, AutoClose.INPUT);
    return baos.toString(charsetName);
  }

  public static void copy(InputStream is, OutputStream os, AutoClose stream) throws IOException {
    byte[] content = new byte[4096];

    try {
      while (true) {
        int size = is.read(content);

        if (size == -1) {
          break;
        } else {
          os.write(content, 0, size);
        }
      }
    } finally {
      stream.close(is);
      stream.close(os);
    }
  }

  public static enum AutoClose {
    NONE,

    INPUT,

    OUTPUT,

    INPUT_OUTPUT;

    public void close(InputStream is) {
      if (this == INPUT || this == INPUT_OUTPUT) {
        if (is != null) {
          try {
            is.close();
          } catch (IOException e) {
            // ignore it
          }
        }
      }
    }

    public void close(OutputStream os) {
      if (this == OUTPUT || this == INPUT_OUTPUT) {
        if (os != null) {
          try {
            os.close();
          } catch (IOException e) {
            // ignore it
          }
        }
      }
    }
  }

  /**
   * 递归删除目录下的所有文件及子目录下所有文件
   *
   * @param dir 将要删除的文件目录
   * @return boolean Returns "true" if all deletions were successful.
   * If a deletion fails, the method stops attempting to
   * delete and returns "false".
   */
  public static boolean deleteDir(File dir) {
    if (dir.isDirectory()) {
      String[] children = dir.list();
      //递归删除目录中的子目录下
      for (int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }
    // 目录此时为空，可以删除
    return dir.delete();
  }


  public static String getMd5(String filePath) throws Exception {
    return DigestUtils.md5Hex(String.valueOf(new FileInputStream(filePath)));
  }

  public static byte[] readBytes(String filePath) throws IOException {
    InputStream in = null;
    BufferedInputStream buffer = null;
    DataInputStream dataIn = null;
    ByteArrayOutputStream bos = null;
    DataOutputStream dos = null;
    byte[] bArray = null;
    try {
      in = new FileInputStream(filePath);
      buffer = new BufferedInputStream(in);
      dataIn = new DataInputStream(buffer);
      bos = new ByteArrayOutputStream();
      dos = new DataOutputStream(bos);
      byte[] buf = new byte[1024];
      while (true) {
        int len = dataIn.read(buf);
        if (len < 0)
          break;
        dos.write(buf, 0, len);
      }
      bArray = bos.toByteArray();
    } catch (Exception e) {
      return null;
    } finally {
      if (in != null)
        in.close();
      if (dataIn != null)
        dataIn.close();
      if (buffer != null)
        buffer.close();
      if (bos != null)
        bos.close();
      if (dos != null)
        dos.close();
    }
    return bArray;
  }


  /**
   * <p>
   * Deletes a file or directory and all contents recursively.
   * </p>
   * <p>
   * If the file argument is a symbolic link the link will be deleted but not
   * the target of the link. If the argument is a directory, symbolic links
   * within the directory will not be followed.
   * </p>
   *
   * @param file the file to delete
   * @throws IOException if an I/O error occurs
   * @see #deleteDirectoryContents
   */
  public static void deleteRecursively(File file) throws IOException {
    if (!file.exists()) return;
    if (file.isDirectory()) {
      deleteDirectoryContents(file);
    }
    if (!file.delete()) {
      throw new IOException("Failed to delete " + file);
    }
  }

  /**
   * <p>
   * Deletes all the files within a directory. Does not delete the directory
   * itself.
   * </p>
   * <p>
   * If the file argument is a symbolic link or there is a symbolic link in
   * the path leading to the directory, this method will do nothing. Symbolic
   * links within the directory are not followed.
   * </p>
   *
   * @param directory the directory to delete the contents of
   * @throws IllegalArgumentException if the argument is not a directory
   * @throws IOException              if an I/O error occurs
   * @see #deleteRecursively
   */
  public static void deleteDirectoryContents(File directory) throws IOException {
    if (!directory.exists()) {
      return;
    }
    //Preconditions.checkArgument(directory.isDirectory(), "Not a directory: %s", directory);
    // Symbolic links will have different canonical and absolute paths
    if (!directory.getCanonicalPath().equals(directory.getAbsolutePath())) {
      return;
    }
    File[] files = directory.listFiles();
    if (files == null) {
      throw new IOException("Error listing files for " + directory);
    }
    for (File file : files) {
      deleteRecursively(file);
    }
  }

  /**
   * copy file path
   * @param sourcePath String source
   * @param targetPath String target
   * @return void
   */
  public static void copyFolder(String sourcePath, String targetPath) throws IOException{


    File f = new File(targetPath);
    if (f.exists()) {
      f.delete();
    }
    (new File(targetPath)).mkdirs();
    File a=new File(sourcePath);
    String[] file=a.list();
    File temp=null;
    for (int i = 0; i < file.length; i++) {
      if(sourcePath.endsWith(File.separator)){
        temp=new File(sourcePath+file[i]);
      }
      else{
        temp=new File(sourcePath+File.separator+file[i]);
      }

      if (file[i].startsWith(".")) {
        continue;
      }
      if(temp.isFile()){
        FileInputStream input = new FileInputStream(temp);
        FileOutputStream output = new FileOutputStream(targetPath + "/" +
          (temp.getName()).toString());
        byte[] b = new byte[1024 * 5];
        int len;
        while ( (len = input.read(b)) != -1) {
          output.write(b, 0, len);
        }
        output.flush();
        output.close();
        input.close();
      }
      if(temp.isDirectory()){
        copyFolder(sourcePath+"/"+file[i],targetPath+"/"+file[i]);
      }
    }
  }
}
