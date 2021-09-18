package com.dynamichub.yash.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class PictureStatus extends AppCompatActivity {

    LinearLayout layoutMenuicon;
    ImageView particularimage;
    ImageButton downloadstatus,sharestatus;
    Dialog dialog;

    ArrayList<String> pathdownloads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_status);

        particularimage=findViewById(R.id.particularimage);
        downloadstatus=findViewById(R.id.downloadStatus);
        sharestatus=findViewById(R.id.shareStatus);


        layoutMenuicon=findViewById(R.id.layout_menu_icon);
        dialog=new Dialog(this);
        Button dialogclosebutton= dialog.findViewById(R.id.backButton);
        Button home=dialog.findViewById(R.id.Home);

        pathdownloads=new ArrayList<>();



        layoutMenuicon.setOnClickListener(new View.OnClickListener() {
            //On click at the meenu icon at the top of the bar

            @Override
            public void onClick(View view) {
                openMenu();
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

        sharestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri screenshotUri = Uri.parse(destpath + filename);

                sharingIntent.setType("image/jpeg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
            }
        });

        downloadstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
            FileUtils.copyFileToDirectory(file1,destpath2);

            pathdownloads.add(file);
            prefconfig.writelistinpref(getApplicationContext(),pathdownloads);



                }catch(IOException e){
                    e.printStackTrace();
                }

                Log.d("pathss",destpath);
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


    public void openMenu(){
        //This Function Opens The Menu
        dialog.setContentView(R.layout.menu_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.START);
        dialog.getWindow().setLayout(920, WindowManager.LayoutParams.MATCH_PARENT);

        dialog.show();
    }

    public void closedialog(View view){
        dialog.dismiss();
    }



    public void iconHomePage(View view){
        //When someone clicks on icon of menu of home this function runs
        dialog.dismiss();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void homePage(View view){
        //When someone clicks on button of menu of home this function runs
        dialog.dismiss();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void iconDownload(View view){
        //When someone clicks on icon of menu of home this function runs
        dialog.dismiss();
        Intent intent=new Intent(this,Downloads.class);
        startActivity(intent);
    }

    public void download(View view){
        //When someone clicks on button of menu of home this function runs
        dialog.dismiss();
        Intent intent=new Intent(this,Downloads.class);
        startActivity(intent);
    }

    public void iconhowto(View view){
        dialog.dismiss();
        Dialog dialog=new Dialog(PictureStatus.this);
        dialog.setContentView(R.layout.howtolayout);
        dialog.show();

    }

    public void howtouse(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.howtolayout);

        dialog2.show();

    }


    public void privacyicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.privacylayout);

        dialog2.show();

    }

    public void privacypolicybtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.privacylayout);

        dialog2.show();

    }

    public void termsicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.termslayout);

        dialog2.show();

    }

    public void termsbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.termslayout);

        dialog2.show();

    }

    public void abticon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.aboutlayout);

        dialog2.show();

    }

    public void abtbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(PictureStatus.this);
        dialog2.setContentView(R.layout.aboutlayout);

        dialog2.show();

    }

}