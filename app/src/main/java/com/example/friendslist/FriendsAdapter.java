package com.example.friendslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.StudentsViewHolder> {

    private final List<Friend> friends;
    private final Listener onClickListener;

    public FriendsAdapter(List<Friend> friends, Listener listener) {
        this.friends = friends;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);         //Устанавливаю во ViewHolder свой item описанный в xml
        //Устанавливаем как listener айтема реализацию listener, которую мы передаем в конструктор адаптера. В него передаем view.getTag, котоырй хранит информацию об айтеме
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onStudentItemClick((Friend) view.getTag());
            }
        });
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.bind(friend);
        holder.itemView.setTag(friend);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    static class StudentsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView profilePhoto;
        private final TextView fullName;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePhoto = itemView.findViewById(R.id.circleProfilePhoto);
            fullName = itemView.findViewById(R.id.fullName);
        }

        public void bind(Friend friend){
            profilePhoto.setImageResource(friend.getImageId());
            fullName.setText(String.format("%s %s", friend.getFirstName(), friend.getSecondName()));
        }
    }

    //Интерфейс, метод которого нужно реализовать. он и будет listener
    interface Listener{
        void onStudentItemClick(Friend friend);
    }
}
