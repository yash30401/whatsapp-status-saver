package com.dynamichub.yash.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
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

        layoutMenuicon.setOnClickListener(new View.OnClickListener() {
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
}