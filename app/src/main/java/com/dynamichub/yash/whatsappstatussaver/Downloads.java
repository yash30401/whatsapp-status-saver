package com.dynamichub.yash.whatsappstatussaver;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Downloads extends AppCompatActivity {


    LinearLayout layoutMenuicon;
    Dialog dialog;
    downloadsAdapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    File[] files;

    ImageButton deletedButton,sharedownloadbutton;


    ArrayList<ModelClass> fileslist=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        recyclerView=findViewById(R.id.recyclerviewDownloads);
        refreshLayout=findViewById(R.id.swipeDownloads);

        setuplayout();




        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.setRefreshing(true);
                setuplayout();
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    },2000);
                }


            }
        });



        layoutMenuicon=findViewById(R.id.layout_menu_icon);
        dialog=new Dialog(this);
        Button dialogclosebutton= dialog.findViewById(R.id.backButton);
        Button home=dialog.findViewById(R.id.Home);

        deletedButton=findViewById(R.id.deletedownloadsbutton);
        sharedownloadbutton=findViewById(R.id.shareDownloadButton);





        layoutMenuicon.setOnClickListener(new View.OnClickListener() {
            //On click at the meenu icon at the top of the bar

            @Override
            public void onClick(View view) {
                openMenu();
            }
        });







    }


    public void setuplayout() {


        fileslist.clear();
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter=new downloadsAdapter(Downloads.this,getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public ArrayList<ModelClass> getData() {

        ModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WhatsAppStatusSaver/";
        File targetDirector = new File(targetPath);
        files = targetDirector.listFiles();





        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            f = new ModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());

            f.setFilename(file.getName());
            if (!f.getUri().toString().endsWith(".nomedia")) {

                fileslist.add(f);

            }

        }

        return fileslist;

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
        Dialog dialog=new Dialog(Downloads.this);
        dialog.setContentView(R.layout.howtolayout);
        dialog.show();

    }

    public void howtouse(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.howtolayout);

        dialog2.show();

    }


    public void privacyicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.privacylayout);

        dialog2.show();

    }

    public void privacypolicybtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.privacylayout);

        dialog2.show();

    }

    public void termsicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.termslayout);

        dialog2.show();

    }

    public void termsbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.termslayout);

        dialog2.show();

    }

    public void abticon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.aboutlayout);

        dialog2.show();

    }

    public void abtbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(Downloads.this);
        dialog2.setContentView(R.layout.aboutlayout);

        dialog2.show();

    }

}