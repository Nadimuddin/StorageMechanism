package com.nexteducation.storagemechanism.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Nadimuddin on 23/12/16.
 */
public class FileHandling
{
    private static final String TAG = "FileHandling";
    /*public File createFile()throws Exception
    {
        File tempFile = Environment.getExternalStorageDirectory();
        tempFile = new File(tempFile.getAbsolutePath()+"/DemoFolder", "File.txt");
        if(!tempFile.exists())
            tempFile.mkdirs();
        else if(tempFile.exists())
            tempFile.delete();
        tempFile.createNewFile();

        return tempFile;
    }*/

    public String readFile()throws Exception
    {
        String directory = Environment.getExternalStorageDirectory().getAbsolutePath();
        StringBuilder sb = null;
        String line, content = null;

        File file = new File(directory+"/File.txt");
        if(file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(directory + "/File.txt"));
            sb = new StringBuilder();
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
                if (line != null)
                    sb.append('\n');
            }
        }
        if(sb != null)
            content = sb.toString();
        return content;
    }

    public boolean writeFile(String id, String name, String address, String designation)throws Exception
    {
        String directory = Environment.getExternalStorageDirectory().getAbsolutePath();

        String newData = id+" "+name+" "+address+" "+designation;
        String data = null;

        try {
            String oldData = readFile();

            if(oldData != null)
                data = oldData+"\n"+newData;
            else
                data = newData;

            //isWriteCompleted = file.writeFile(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter(directory+"/File.txt");
        writer.write(data);
        writer.close();
        return true;
    }

    public void deleteLine(int id)
    {
        String fileContent = null;
        String newFileContent = null;

        try
        {
            fileContent = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String str[] = fileContent.split("\n");

        for(int i=0; i<str.length; i++)
        {
            int firstChar = (int)str[i].charAt(0);
            if((int)str[i].charAt(0) == id)
            {
                Log.i(TAG, "deleteLine: ");
            }
            else
            {
                if(i == 0)
                    newFileContent = str[i];
                else
                    newFileContent = newFileContent+"\n"+str[i];
            }
        }
        writeFile(newFileContent);
    }

    private void writeFile(String data)
    {
        String directory = Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            FileWriter writer = new FileWriter(directory + "/File.txt");
            writer.write(data);
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
