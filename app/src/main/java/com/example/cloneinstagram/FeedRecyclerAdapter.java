package com.example.cloneinstagram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;


    public FeedRecyclerAdapter(ArrayList<String> userEmailList, ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        this.userEmailList = userEmailList;
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }



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
      Picasso.get().load(userImageList.get(position)).into(holder.imageView);

    }

    class PostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView user_emailText;
        TextView commentText;

        public PostHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.recyclerView_imageView);
            user_emailText=itemView.findViewById(R.id.recyclerView_row_userEmail_Text);
            commentText=itemView.findViewById(R.id.recyclerView_row_comment_Text);

        }
    }

}
