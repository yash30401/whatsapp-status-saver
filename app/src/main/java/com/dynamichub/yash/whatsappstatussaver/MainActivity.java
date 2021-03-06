package com.dynamichub.yash.whatsappstatussaver;



import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.Manifest;

import android.app.Dialog;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;



import java.io.File;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    LinearLayout layoutMenuicon;
    Dialog dialog;
    Adapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    File[] files;

    ArrayList<ModelClass> fileslist=new ArrayList<>();

    ActivityResultLauncher<Intent> getpermission;

    Button howto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView=findViewById(R.id.recyclerview);
        refreshLayout=findViewById(R.id.swipe);



        getpermission=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==MainActivity.RESULT_OK){
                    Toast.makeText(getApplicationContext(),"Permission Given",Toast.LENGTH_LONG).show();
                }
            }
        });

        takePermission();
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





        howto=findViewById(R.id.howto);
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

        howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.howtolayout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
            }
        });


    }

    public void takePermissions() {

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {

                Intent intent=new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                getpermission.launch(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);

        }
    }

    public  boolean isPermissionGranted(){

        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }else{
            int readExternalStoragePermission= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return readExternalStoragePermission==PackageManager.PERMISSION_GRANTED;
        }


    }

    public void takePermission(){

        if(isPermissionGranted()){
            Log.d("Permission","Permission Already given");
        }else{
            takePermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0){
            if(requestCode==101){
                boolean readExternalStorage=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                if(readExternalStorage){
                    Log.d("Permissions","Permission allow in android 10 or below");
                }else{
                    takePermissions();
                }
            }
        }

    }

    public void setuplayout() {


        fileslist.clear();
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter=new Adapter(MainActivity.this,getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public ArrayList<ModelClass> getData() {

        ModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses";
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
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.howtolayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void howtouse(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.howtolayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void privacyicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.setContentView(R.layout.privacylayout);

        dialog2.show();

    }

    public void privacypolicybtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);

        dialog2.setContentView(R.layout.privacylayout);

        dialog2.show();

    }

    public void termsicon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.termslayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void termsbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.termslayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void abticon(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.aboutlayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }

    public void abtbtn(View view){
        dialog.dismiss();
        Dialog dialog2=new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.aboutlayout);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();

    }


}