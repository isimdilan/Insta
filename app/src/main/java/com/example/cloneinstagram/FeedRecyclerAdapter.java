package com.example.cloneinstagram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;
   // private ArrayList<String> commentImage;
    //private ArrayList<String> commentComment;

    public FeedRecyclerAdapter(ArrayList<String> userEmailList, ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        this.userEmailList = userEmailList;
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }

    /*public FeedRecyclerAdapter(ArrayList<String> commentImage, ArrayList<String> commentComment) {
        this.commentImage = commentImage;
        this.commentComment = commentComment;
    }*/

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new PostHolder(view);
    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
      holder.user_emailText.setText(userEmailList.get(position));
      holder.commentText.setText(userCommentList.get(position));
      //holder.commentEdit.setText(commentComment.get(position));
      Picasso.get().load(userImageList.get(position)).into(holder.imageView);
      //Picasso.get().load(commentImage.get(position)).into(holder.commentImage);
    }

    class PostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView user_emailText;
        TextView commentText;
        //EditText commentEdit;
       // ImageView commentImage;
        public PostHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.recyclerView_imageView);
            user_emailText=itemView.findViewById(R.id.recyclerView_row_userEmail_Text);
            commentText=itemView.findViewById(R.id.recyclerView_row_comment_Text);
           // commentEdit=itemView.findViewById(R.id.comment_Edit_text);
            //commentImage=itemView.findViewById(R.id.comment_image);
        }
    }

}
