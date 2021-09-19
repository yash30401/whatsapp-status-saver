package com.dynamichub.yash.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class VideoStatus extends AppCompatActivity {
    VideoView particularVideo;
    ImageButton downloadstatus,sharestatus;

    LinearLayout layoutMenuicon;
    Dialog dialog;

    Button howto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_status);

        particularVideo=findViewById(R.id.particularVideo);
        downloadstatus=findViewById(R.id.downloadStatus);
        sharestatus=findViewById(R.id.shareStatusVideo);
        layoutMenuicon=findViewById(R.id.layout_menu_icon);
        dialog=new Dialog(this);
        Button dialogclosebutton= dialog.findViewById(R.id.backButton);
        Button home=dialog.findViewById(R.id.Home);

        layoutMenuicon.setOnClickListener(new View.OnClickListener() {
            //On click at the meenu icon at the top of the bar

            @Override
            public void onClick(View view) {
                openMenu();
            }
        });


        howto=findViewById(R.id.howto);

        howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(VideoStatus.this);
                dialog.setContentView(R.layout.howtolayout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
            }
        });



        Intent intent=getIntent();
        String destpath=intent.getStringExtra("DEST_PATH_VIDEO");
        String file=intent.getStringExtra("FILE_VIDEO");
        String uri=intent.getStringExtra("URI_VIDEO");
        String filename=intent.getStringExtra("FILENAME_VIDEO");

        File destpath2=new File(destpath);
        File file1=new File(file);

        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(particularVideo);

        Uri uri1=Uri.parse(uri);

        particularVideo.setMediaController(mediaController);
        particularVideo.setVideoURI(uri1);
        particularVideo.requestFocus();
        particularVideo.start();

        sharestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("video/mp4"); //If it is a 3gp video use ("video/3gp")
                Uri uri = Uri.parse(destpath+filename);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(sharingIntent, "Share Video!"));
            }
        });

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

                Dialog dialog=new Dialog(VideoStatus.this);
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
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.howtolayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void howtouse(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.howtolayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void privacyicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.privacylayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void privacypolicybtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.privacylayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void termsicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.termslayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void termsbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.termslayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void abticon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.aboutlayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void abtbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(VideoStatus.this);
        dialog2.setContentView(R.layout.aboutlayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

}