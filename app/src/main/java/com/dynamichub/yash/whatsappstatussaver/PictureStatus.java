package com.dynamichub.yash.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class PictureStatus extends AppCompatActivity {


    ImageView particularimage;
    ImageButton downloadstatus,sharestatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_status);

        particularimage=findViewById(R.id.particularimage);
        downloadstatus=findViewById(R.id.downloadStatus);
        sharestatus=findViewById(R.id.shareStatus);

        sharestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_LONG).show();
            }
        });


        Intent intent=getIntent();
        String destpath=intent.getStringExtra("DEST_PATH");
        String file=intent.getStringExtra("FILE");
        String uri=intent.getStringExtra("URI");
        String filename=intent.getStringExtra("FILENAME");

        File destpath2=new File(destpath);
        File file1=new File(file);

        Glide.with(getApplicationContext()).load(uri).into(particularimage);

        downloadstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    org.apache.commons.io.FileUtils.copyFileToDirectory(file1,destpath2);
                }catch(IOException e){
                    e.printStackTrace();
                }


                MediaScannerConnection.scanFile(getApplicationContext(), new String[]{destpath + filename}, new String[]{"*/*"},
                        new MediaScannerConnection.MediaScannerConnectionClient() {
                            @Override
                            public void onMediaScannerConnected() {


                            }

                            @Override
                            public void onScanCompleted(String s, Uri uri) {

                            }
                        });

                Dialog dialog=new Dialog(PictureStatus.this);
                dialog.setContentView(R.layout.downloaddialog);
                dialog.show();

                Button okbutton=dialog.findViewById(R.id.okaybutton);

                okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });




    }
}