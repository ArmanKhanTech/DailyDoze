package com.android.dailydoze.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dailydoze.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

@SuppressWarnings("ALL")
public class BackupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        if (Build.VERSION.SDK_INT >= 30){
            if (!Environment.isExternalStorageManager()){
                Intent getPermission = new Intent();
                getPermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(getPermission);
            }
        }

        //exportDB();
        //importDB();

    }
    //importing database
    private void importDB() {

        File sd = new File(Objects.requireNonNull(this.getFilesDir().getParentFile()).getPath() + "/databases");

        if (sd.canWrite()) {
            final File[] databases = new File(Environment.getExternalStorageDirectory() + "/Daily Doze").listFiles();
            assert databases != null;

            for (File databaseFile: databases) {
                final String backupFilename = databaseFile.getName();
                File backupFile = new File(sd, backupFilename);

                FileChannel inputChannel = null;
                FileChannel outputChannel = null;

                try {
                    inputChannel = new FileInputStream(databaseFile.getAbsolutePath()).getChannel();
                    outputChannel = new FileOutputStream(backupFile).getChannel();
                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputChannel != null) {
                        try {
                            inputChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (outputChannel != null) {
                        try {
                            outputChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            //
        }

    }
    //exporting database
    private void exportDB() {
        File sd = new File(Environment.getExternalStorageDirectory() + "/Daily Doze");

        if(!sd.exists()){
            if(sd.mkdir()){
                //
            }
        }

        if (sd.canWrite()) {
            final File[] databases = new File(Objects.requireNonNull(this.getFilesDir().getParentFile()).getPath() + "/databases").listFiles();
            assert databases != null;

            for (File databaseFile: databases) {
                final String backupFilename = databaseFile.getName() + ".db";
                File backupFile = new File(sd, backupFilename);
                FileChannel inputChannel = null;
                FileChannel outputChannel = null;

                try {
                    inputChannel = new FileInputStream(databaseFile.getAbsolutePath()).getChannel();
                    outputChannel = new FileOutputStream(backupFile).getChannel();
                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputChannel != null) {
                        try {
                            inputChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (outputChannel != null) {
                        try {
                            outputChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            //
        }
    }
}