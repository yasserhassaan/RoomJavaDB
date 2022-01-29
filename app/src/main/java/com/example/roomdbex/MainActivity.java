package com.example.roomdbex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roomdbex.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;  //dataBinding
    private List<Post> postlList = new ArrayList<>();
    private PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final PostsDataBase postsDataBase= PostsDataBase.getInstance(this);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main); //dataBinding
        activityMainBinding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=activityMainBinding.titleEditText.getText().toString();
                String body=activityMainBinding.bodyEditText.getText().toString();
                postsDataBase.postDao().insertPost(new Post(new User(1,"yasser"),title,body))
                        .subscribeOn(Schedulers.computation())       //rx java new thread
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                                //Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });
        activityMainBinding.getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postsDataBase.postDao().getPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Post> posts) {
                             //   Toast.makeText(MainActivity.this, "get", Toast.LENGTH_SHORT).show();
                                postlList=posts;
                                postsAdapter = new PostsAdapter(postlList);
                                activityMainBinding.recyclerViewGet.setAdapter(postsAdapter);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });








        /*postlList.add(new Post(1,"yas","hassaan"));
        postlList.add(new Post(1,"yas","hassaan"));
        postlList.add(new Post(1,"yas","hassaan"));
        */

      //  postsDataBase.postDao().insertPost(new Post(1,"yasser","room test"));

    }
}