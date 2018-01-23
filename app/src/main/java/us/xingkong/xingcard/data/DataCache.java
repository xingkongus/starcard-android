package us.xingkong.xingcard.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.bean.Contacts;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class DataCache {
    private static String DATA_FILE_NAME = "data.db";

    private static DataCache INSTANCE;

    File dataFile = new File(XingCardAPP.getAppContext().getFilesDir(), DATA_FILE_NAME);
    Gson gson = new Gson();

    private DataCache() {
    }

    public static DataCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataCache();
        }
        return INSTANCE;
    }

    public boolean hasDataCache() {
        return dataFile.exists();
    }

    public Contacts readDatas() {
        // Hard code adding some delay, to distinguish reading from memory and reading disk clearly
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Reader reader = new FileReader(dataFile);
            return gson.fromJson(reader, new TypeToken<Contacts>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeDatas(Contacts contacts) {
        String json = gson.toJson(contacts);
        try {
            if (!dataFile.exists()) {
                try {
                    dataFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Writer writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        dataFile.delete();
    }

}
