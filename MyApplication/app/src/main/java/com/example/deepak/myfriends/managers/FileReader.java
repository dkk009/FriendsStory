package com.example.deepak.myfriends.managers;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.deepak.myfriends.MyFriendsApp;
import com.example.deepak.myfriends.interfaces.IDataModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


/**
 *
 */
public class FileReader {
    private String mPath;
    private IntrFileReader mFileReaderCallaBack;
    private Class mClass;

    public interface IntrFileReader {
        public void onCompleted(Object fileData);

        public void onError(Exception e);

    }

    public FileReader() {

    }
    public FileReader(IntrFileReader fileReader) {
        mFileReaderCallaBack = fileReader;
    }
    public FileReader(String path, IntrFileReader fileReader) {
        mPath = path;
        mFileReaderCallaBack = fileReader;
    }


    public String readFromFile(InputStream file) throws Exception {
        String data = "";
        String line = "";
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(file));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }
            return data;

        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void readFromAsset(String fileName,IntrFileReader fileReader,Class cls) {
        mFileReaderCallaBack = fileReader;
        mClass = cls;
        if (TextUtils.isEmpty(fileName)) {
            mFileReaderCallaBack.onError(new IOException("File Not found:" + fileName));
            return;
        }
        AssetManager assetManager = MyFriendsApp.getInstance().getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            ReadTask readTask = new ReadTask(inputStream);
            readTask.execute();
        } catch (IOException e) {
            e.printStackTrace();
            if (null != mFileReaderCallaBack) {
                mFileReaderCallaBack.onError(e);
            }

        }
    }
    public void readFromAsset(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            mFileReaderCallaBack.onError(new IOException("File Not found:" + fileName));
            return;
        }
        AssetManager assetManager = MyFriendsApp.getInstance().getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            ReadTask readTask = new ReadTask(inputStream);
            readTask.execute();
        } catch (IOException e) {
            e.printStackTrace();
            if (null != mFileReaderCallaBack) {
                mFileReaderCallaBack.onError(e);
            }

        }
    }


    public class ReadTask extends AsyncTask<Void, Void, FileContent> {

        private InputStream mInputStream;

        public ReadTask(InputStream stream) {
            mInputStream = stream;
        }

        @Override
        protected FileContent doInBackground(Void... voids) {
            FileContent fileContent = new FileContent();
            try {
                String fileData = readFromFile(mInputStream);
                Gson gson = new Gson();
                fileContent.setContent(gson.fromJson(fileData,mClass));
                fileContent.setStatus(true);
                return fileContent;
            } catch (Exception e) {
                e.printStackTrace();
                fileContent.setStatus(false);
                fileContent.setException(e);

            }
            return fileContent;
        }

        @Override
        protected void onPostExecute(FileContent fileContent) {
            super.onPostExecute(fileContent);
            if (null == mFileReaderCallaBack) {
                return;
            }
            if (fileContent.isStatus()) {
                mFileReaderCallaBack.onCompleted(fileContent.content);
            } else {
                mFileReaderCallaBack.onError(fileContent.exception);
            }
        }
    }

    private class FileContent {
        private Object content;
        private boolean status;
        private Exception exception;

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }
    }
}
