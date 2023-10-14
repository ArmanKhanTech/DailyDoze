package com.android.dailydoze.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.android.dailydoze.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

@SuppressWarnings("ALL")
public class BackupActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView, loading;
    AppCompatButton b1, b2;
    ScrollView scrollView;
    String what;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        lottieAnimationView = findViewById(R.id.backup_anim);
        lottieAnimationView.setRenderMode(RenderMode.HARDWARE);
        lottieAnimationView.playAnimation();

        loading = findViewById(R.id.loading_anim);
        scrollView = findViewById(R.id.backup_scroll);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.pauseAnimation();
                scrollView.setVisibility(View.VISIBLE);
            }
        }, 2000);

        b1 = findViewById(R.id.importt);
        b2 = findViewById(R.id.export);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                what = "import";
                Load load = new Load();
                load.execute();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                what = "export";
                Load load = new Load();
                load.execute();
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    private final class Load extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            scrollView.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            loading.playAnimation();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(what.equals("import")){
                importDB();
            } else if(what.equals("export")){
                exportDB();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void results) {
            super.onPostExecute(results);

            openDialog(what);

            scrollView.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }

    private void importDB() {

        File sd = new File(Objects.requireNonNull(this.getFilesDir().getParentFile()).getPath() + "/databases");

        if (sd.canWrite()) {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File[] databases = new File(path + "/" + "DailyDoze").listFiles();

            if(databases != null) {
                if(databases.length == 0) {
                    what = "No files found.";
                    return;
                }
            } else {
                what = "No files found.";
                return;
            }

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

                    what = "Done Imported";
                    return;
                }
            }
        } else {
            what = "Error";
            return;
        }
    }

    private void exportDB() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File sd = new File(path + "/" + "DailyDoze");

        if(!sd.exists()){
            sd.mkdir();
        }

        if (sd.canWrite()) {
            final File[] databases = new File(Objects.requireNonNull(this.getFilesDir().getParentFile()).getPath() + "/databases").listFiles();

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

                    what = "Done Exported";
                    return;
                }
            }
        } else {
            what = "Error";
            return;
        }
    }

    public void openDialog(String msg){
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        View popupView = layoutInflater.inflate(R.layout.get_weight, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        EditText hrs = popupView.findViewById(R.id.weight);
        TextView tv = popupView.findViewById(R.id.timing);
        Button okay = popupView.findViewById(R.id.save);

        hrs.setVisibility(View.GONE);
        okay.setText("Okay");

        if(msg == "Done Imported") {
            tv.setText("Your data is imported successfully.");
        } else if(msg == "Done Exported") {
            tv.setText("Your data is exported successfully.");
        } else if(msg == "Error") {
            tv.setText("An exception occured. Please try again.");
            okay.setText("Try Again");
        } else if(msg == "No files found.") {
            tv.setText("No files were found.");
        }

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
        dimBehind(popupWindow);
    }

    public static void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);
    }

    public void backupFinish(View v){
        finish();
    }
}