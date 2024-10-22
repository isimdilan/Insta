package com.example.cloneinstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class FeedActivitys extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFB;
    ArrayList<String > userImageFB;
    FeedRecyclerAdapter feedRecyclerAdapter;





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.insta_options_menu,menu);


        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_post) {

            Intent intent_upload=new Intent(FeedActivitys.this,UploadActivity.class);
            startActivity(intent_upload);

        }else  if(item.getItemId()==R.id.sign_out){

        firebaseAuth.signOut();

        Intent intent_signUp=new Intent(FeedActivitys.this,SignUpActivity.class);
        startActivity(intent_signUp);
        finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);


        userCommentFB=new ArrayList<>();
        userEmailFromFB=new ArrayList<>();
        userImageFB=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        getDataFromFirestore();



        RecyclerView recyclerView=findViewById(R.id.recycler_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedRecyclerAdapter=new FeedRecyclerAdapter(userEmailFromFB,userCommentFB,userImageFB);
        recyclerView.setAdapter(feedRecyclerAdapter);


     /**/



    }



    public void getDataFromFirestore(){
        CollectionReference collectionReference=firebaseFirestore.collection("Posts");
        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(FeedActivitys.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();;
                }

              if(queryDocumentSnapshots!=null){
                  for (DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments()){
                      Map<String,Object> data=snapshot.getData();
                      //Casting
                      String comment=(String) data.get("comment");
                      String  userEmail=(String)data.get("useremail");
                      String downloadurl=(String)data.get("downloaUrl");

                      userCommentFB.add(comment);
                      userEmailFromFB.add(userEmail);
                      userImageFB.add(downloadurl);
                      feedRecyclerAdapter.notifyDataSetChanged();

                  }
              }
            }
        });
    }

    public void delete(View view){
        AlertDialog.Builder alert=new AlertDialog.Builder(FeedActivitys.this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Task<Void> collectionReference=firebaseFirestore.collection("Posts").document().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(FeedActivitys.this,"Not Delete",Toast.LENGTH_LONG).show();
            }
        });
        alert.show();


    }

}