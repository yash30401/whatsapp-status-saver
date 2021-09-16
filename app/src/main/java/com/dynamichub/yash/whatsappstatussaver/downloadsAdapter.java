package com.dynamichub.yash.whatsappstatussaver;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class downloadsAdapter extends RecyclerView.Adapter<downloadsAdapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> filelist;

    public int positionofitem;



    public downloadsAdapter(Context context, ArrayList<ModelClass> filelist) {
        this.context = context;
        this.filelist = filelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.downloadlayout, null, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final ModelClass modelClass = filelist.get(position);


        Glide.with(context).load(modelClass.getUri()).into(holder.mainstatus);

        positionofitem = position;

        holder.mainstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkFolder();
                if (modelClass.getUri().toString().endsWith(".mp4")) {


                    final String path = filelist.get(position).getPath();

                    String destpath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER;
                    Intent intent = new Intent(context, VideoStatus.class);
                    intent.putExtra("DEST_PATH_VIDEO", destpath);
                    intent.putExtra("FILE_VIDEO", path);
                    intent.putExtra("FILENAME_VIDEO", modelClass.getFilename());
                    intent.putExtra("URI_VIDEO", modelClass.getUri().toString());
                    context.startActivity(intent);


                } else {


                    final String path = filelist.get(position).getPath();
                    String destpath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER;
                    Intent intent = new Intent(context, PictureStatus.class);
                    intent.putExtra("DEST_PATH", destpath);
                    intent.putExtra("FILE", path);
                    intent.putExtra("FILENAME", modelClass.getFilename());
                    intent.putExtra("URI", modelClass.getUri().toString());
                    context.startActivity(intent);


                }
            }
        });

        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String root = Environment.getExternalStorageDirectory().toString();
                File file = new File(root + "/WhatsAppStatusSaver/"+modelClass.getFilename());
                file.delete();
                filelist.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,filelist.size());


            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri screenshotUri = Uri.parse(modelClass.getPath());

                sharingIntent.setType("image/jpeg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                context.startActivity(Intent.createChooser(sharingIntent, "Share image using"));
            }
        });






    }

    private void checkFolder() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER;
        File dir = new File(path);

        boolean isDirCreated = dir.exists();

        if (!isDirCreated) {
            isDirCreated = dir.mkdir();
        }

        if (isDirCreated) {
            Log.d("Folder", "Already Created");
        }
    }

    @Override
    public int getItemCount() {
        return filelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mainstatus, play;
        ImageButton deleteView, share;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainstatus = itemView.findViewById(R.id.downloadsimage);
            play = itemView.findViewById(R.id.play);
            share = itemView.findViewById(R.id.shareDownloadButton);
           deleteView=itemView.findViewById(R.id.deletedownloadsbutton);






        }







    }
}