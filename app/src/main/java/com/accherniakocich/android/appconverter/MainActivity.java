package com.accherniakocich.android.appconverter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class MainActivity extends Activity {

    final String FILENAME = "file";
    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,
            ed11,ed12,ed13,ed14,ed15,ed16,ed17,ed18,ed19,ed20,
            ed21,ed22,ed23,ed24,ed25,ed26,ed27,ed28,ed29,ed30;
    private Button button_save,button_send,button_clean;
    private String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,
            s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,
            s21,s22,s23,s24,s25,s26,s27,s28,s29,s30;

    private static final String LOG_TAG = "MyLogs";
    public static final String FONT = "fonts/FreeSans.ttf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ed1 = (EditText)findViewById(R.id.et1);
        ed2 = (EditText)findViewById(R.id.et2);
        ed3 = (EditText)findViewById(R.id.et3);
        ed4 = (EditText)findViewById(R.id.et4);
        ed5 = (EditText)findViewById(R.id.et5);
        ed6 = (EditText)findViewById(R.id.et6);
        ed7 = (EditText)findViewById(R.id.et7);
        ed8 = (EditText)findViewById(R.id.et8);
        ed9 = (EditText)findViewById(R.id.et9);
        ed10 = (EditText)findViewById(R.id.et10);
        ed11 = (EditText)findViewById(R.id.et11);
        ed12 = (EditText)findViewById(R.id.et12);
        ed13 = (EditText)findViewById(R.id.et13);
        ed14 = (EditText)findViewById(R.id.et14);
        ed15 = (EditText)findViewById(R.id.et15);
        ed16 = (EditText)findViewById(R.id.et16);
        ed17 = (EditText)findViewById(R.id.et17);
        ed18 = (EditText)findViewById(R.id.et18);
        ed19 = (EditText)findViewById(R.id.et19);
        ed20 = (EditText)findViewById(R.id.et20);
        ed21 = (EditText)findViewById(R.id.et21);
        ed22 = (EditText)findViewById(R.id.et22);
        ed23 = (EditText)findViewById(R.id.et23);
        ed24 = (EditText)findViewById(R.id.et24);
        ed25 = (EditText)findViewById(R.id.et25);
        ed26 = (EditText)findViewById(R.id.et26);
        ed27 = (EditText)findViewById(R.id.et27);
        ed28 = (EditText)findViewById(R.id.et28);
        ed29 = (EditText)findViewById(R.id.et29);
        ed30 = (EditText)findViewById(R.id.et30);

        button_save = (Button)findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                save();
            }
        });
        button_send = (Button)findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        button_clean = (Button) findViewById(R.id.button_claen);
        button_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
    }

    private void clean() {

    }

    private void send() {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("*/*");

        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/mypdf.pdf")));//path of video
        startActivity(Intent.createChooser(emailIntent, "Send file..."));
    }

    private void save() {

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            Log.d(LOG_TAG,"We don't have permission so prompt the user");
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }else{
            Document document;
            String outhPath = Environment.getExternalStorageDirectory()+"/mypdf.pdf";
            try {
                /*PdfWriter.getInstance(document, new FileOutputStream(outhPath));
                document.open();
                document.add(new Paragraph("Тут какой-то текст"));
                document.close();
                Log.d(LOG_TAG,"saved");*/

                document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(outhPath));
                document.open();
                //BaseFont bfComic = BaseFont.createFont("fonts/myfont.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font font = new Font (BaseFont.createFont(BaseFont.COURIER,"utf-8",true));
                document.add(new Paragraph("English Русский .",font));

                document.close();

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
