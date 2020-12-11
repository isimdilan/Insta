package com.example.cloneinstagram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.PostHolder>{

    private ArrayList<String> commentImage;
    private ArrayList<String> commentComment;

    public CommentRecyclerAdapter(ArrayList<String> commentImage, ArrayList<String> commentComment) {
        this.commentImage = commentImage;
        this.commentComment = commentComment;
    }

    @NonNull
    @Override
    public CommentRecyclerAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.comment,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentRecyclerAdapter.PostHolder holder, int position) {
        holder.commentEdit.setText(commentComment.get(position));
        Picasso.get().load(commentImage.get(position)).into(holder.commentImage);
    }

    @Override
    public int getItemCount() {
        return commentComment.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        EditText commentEdit;
         ImageView commentImage;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
             commentEdit=itemView.findViewById(R.id.comment_Edit_text);
            commentImage=itemView.findViewById(R.id.comment_image);

        }
    }
}
