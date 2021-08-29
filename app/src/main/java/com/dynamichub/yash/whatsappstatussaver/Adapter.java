package com.dynamichub.yash.whatsappstatussaver;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> filelist;

    public Adapter(Context context, ArrayList<ModelClass> filelist) {
        this.context = context;
        this.filelist = filelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.status_item_layout,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final ModelClass modelClass=filelist.get(position);
        if(modelClass.getUri().toString().endsWith(".mp4")){
            holder.play.setVisibility(View.VISIBLE);
        }else{

            holder.play.setVisibility(View.INVISIBLE);

        }

        Glide.with(context).load(modelClass.getUri()).into(holder.mainstatus);

        holder.mainstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkFolder();
                if(modelClass.getUri().toString().endsWith(".mp4")){


                    final String path=filelist.get(position).getPath();

                    String destpath= Environment.getExternalStorageDirectory().getAbsolutePath()+Constant.SAVE_FOLDER;
                    Intent intent=new Intent(context,VideoStatus.class);
                    intent.putExtra("DEST_PATH_VIDEO",destpath);
                    intent.putExtra("FILE_VIDEO",path);
                    intent.putExtra("FILENAME_VIDEO",modelClass.getFilename());
                    intent.putExtra("URI_VIDEO",modelClass.getUri().toString());
                    context.startActivity(intent);


                }else{

                    final String path=filelist.get(position).getPath();
                    String destpath= Environment.getExternalStorageDirectory().getAbsolutePath()+Constant.SAVE_FOLDER;
                    Intent intent=new Intent(context,PictureStatus.class);
                    intent.putExtra("DEST_PATH",destpath);
                    intent.putExtra("FILE",path);
                    intent.putExtra("FILENAME",modelClass.getFilename());
                    intent.putExtra("URI",modelClass.getUri().toString());
                    context.startActivity(intent);


                }
            }
        });



    }

    private void checkFolder() {
        String path=Environment.getExternalStorageDirectory().getAbsolutePath()+Constant.SAVE_FOLDER;
        File dir=new File(path);

        boolean isDirCreated=dir.exists();

        if(!isDirCreated){
            isDirCreated=dir.mkdir();
        }

        if(isDirCreated){
            Log.d("Folder","Already Created");
        }
    }

    @Override
    public int getItemCount() {
        return filelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mainstatus,play;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainstatus=itemView.findViewById(R.id.thumnailOfStatus);
            play=itemView.findViewById(R.id.play);


        }
    }
}
