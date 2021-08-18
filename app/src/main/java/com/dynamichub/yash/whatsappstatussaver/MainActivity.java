package com.dynamichub.yash.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    LinearLayout layoutMenuicon;
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



    }

    private void openMenu() {
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
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void homePage(View view){
        //When someone clicks on button of menu of home this function runs
    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);
    }

}