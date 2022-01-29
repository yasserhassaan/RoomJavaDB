package com.example.roomdbex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbex.databinding.PostItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
   // private ArrayList<Post> movieList = new ArrayList<>();
   private LayoutInflater layoutInflater;
    private List<Post> post_list ;


    public PostsAdapter(List<Post> post_list) {
        this.post_list = post_list;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        PostItemBinding postItemBinding= DataBindingUtil
                .inflate(layoutInflater,R.layout.post_item,parent,false);

        return new PostsViewHolder(postItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {

        holder.bindPost(post_list.get(position));

    }

    @Override
    public int getItemCount() {
        return post_list.size();
    }



    public class PostsViewHolder extends RecyclerView.ViewHolder {

        private PostItemBinding postItemBinding;

        // view
        public PostsViewHolder(PostItemBinding postItemBinding ) {
            super(postItemBinding.getRoot());
            this.postItemBinding=postItemBinding;
            //view
        }
        private void bindPost(Post post){
            postItemBinding.titleTv.setText(post.getTitle());
            postItemBinding.bodyTv.setText(post.getBody());

        }
    }
}
