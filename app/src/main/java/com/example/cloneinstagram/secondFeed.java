package com.example.cloneinstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nullable;

public class secondFeed extends AppCompatActivity {

    CommentRecyclerAdapter commentRecycler;
    ArrayList<String> imageCommentFB;
    ArrayList<String> commentFB;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_feed);



        imageCommentFB=new ArrayList<>();
        commentFB=new ArrayList<>();

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        getDataFromFirestore1();

        RecyclerView recyclerView1=findViewById(R.id.comment_recycleer);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        commentRecycler=new CommentRecyclerAdapter(imageCommentFB,commentFB);
        recyclerView1.setAdapter(commentRecycler);
    }

    public void getDataFromFirestore1(){
        CollectionReference collectionReference=firebaseFirestore.collection("Posts-second");
        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(secondFeed.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();;
                }

                if(queryDocumentSnapshots!=null){
                    for (DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments()){
                        Map<String,Object> data=snapshot.getData();
                        //Casting
                        String comment=(String) data.get("comment");
                        //String  userEmail=(String)data.get("useremail");
                        String downloadurl=(String)data.get("downloaUrl");

                        commentFB.add(comment);
                        imageCommentFB.add(downloadurl);


                        commentRecycler.notifyDataSetChanged();





                    }
                }
            }
        });
    }
}