package com.example.friendslist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Friend> friends;
    private EditText firstNameView;
    private EditText surNameView;
    private RadioButton radio_male;
    private RadioButton radio_female;
    private ImageView profilePhotoView;
    private FriendsAdapter friendsAdapter;
    private View profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.profile);
        firstNameView = findViewById(R.id.PersonFirstName);
        surNameView = findViewById(R.id.PersonSecondName);
        profilePhotoView = findViewById(R.id.profile_avatar);
        radio_male = findViewById(R.id.radioButton_male);
        radio_female = findViewById(R.id.radioButton_fem);


        profilePhotoView.setTag(R.drawable.empty_profile);
        friends = generateStudents();
        setupRecyclerView();

    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.studentsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsAdapter = new FriendsAdapter(friends, new FriendsAdapter.Listener() {
            @Override
            public void onStudentItemClick(Friend friend) {
                //По нажатии заносит все данные в ui поля студента
                firstNameView.setText(friend.getFirstName());
                surNameView.setText(friend.getSecondName());
                profilePhotoView.setImageResource(friend.getImageId());



                if (friend.getGender().equals(Friend.Gender.MALE)) {
                    radio_male.setChecked(true);
                } else {
                    radio_female.setChecked(true);
                }
            }
        });
        recyclerView.setAdapter(friendsAdapter);
    }

    private void clearProfile(){
        firstNameView.setText("");
        surNameView.setText("");
        profilePhotoView.setImageResource(R.drawable.empty_profile);
    }

    private List<Friend> generateStudents(){
        //Метод дает базовое заполнение для RecyclerView
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend.Builder().withName("Victoria").withSurname("Snegova").withGender(Friend.Gender.FEMALE).withImageId(R.drawable.female_1).build());
        friends.add(new Friend.Builder().withName("Liza").withSurname("Yanbaeva").withGender(Friend.Gender.FEMALE).withImageId(R.drawable.female_2).build());
        friends.add(new Friend.Builder().withName("Daria").withSurname("Neshko").withGender(Friend.Gender.FEMALE).withImageId(R.drawable.female_3).build());
        friends.add(new Friend.Builder().withName("Andrey").withSurname("Kostin").withGender(Friend.Gender.MALE).withImageId(R.drawable.male_1).build());
        friends.add(new Friend.Builder().withName("Alexandr").withSurname("Rytp").withGender(Friend.Gender.MALE).withImageId(R.drawable.male_2).build());
        friends.add(new Friend.Builder().withName("Dmitriy").withSurname("Makeev").withGender(Friend.Gender.MALE).withImageId(R.drawable.male_3).build());
        friends.add(new Friend.Builder().withName("Jack").withSurname("Sparrow").withGender(Friend.Gender.MALE).withImageId(R.drawable.male_1).build());
        friends.add(new Friend.Builder().withName("Ivan").withSurname("Kolnev").withGender(Friend.Gender.MALE).withImageId(R.drawable.male_2).build());
        friends.add(new Friend.Builder().withName("Liya").withSurname("Temeva").withGender(Friend.Gender.MALE).withImageId(R.drawable.male_3).build());
        return friends;
    }

    public void onClickSaveButton(View view) {
        //По нажатии кнопки "Сохранить", создаетеся инстанс класса Student, который впоследствии добавляется в лист
        Friend newFriend = new Friend.Builder().
                withName(firstNameView.getText().toString()).
                withSurname(surNameView.getText().toString()).
                withGender(radio_male.isChecked() ? Friend.Gender.MALE : Friend.Gender.FEMALE).
                withImageId(Integer.parseInt(profilePhotoView.getTag().toString())).
                build();

        //Проверка на заполненность данных
        if(newFriend.isStudentFilled().equals("OK")){
            friends.add(newFriend);
            friendsAdapter.notifyItemInserted(friends.size() - 1); //Оповещаем адаптер об изменении последнего елемента
            clearProfile();
            Toast.makeText(MainActivity.this, "Студент добавлен", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, newFriend.isStudentFilled(), Toast.LENGTH_LONG).show();
        }

    }

    public void onClickDeleteButton(View view) {
        //По нажатии кнопки "Удалить", создаетеся инстанс класса Student, поиск по которому происходит в листе для удаления
        Friend friend = new Friend.Builder().
                withName(firstNameView.getText().toString()).
                withSurname(surNameView.getText().toString()).
                withGender(radio_male.isChecked() ? Friend.Gender.MALE : Friend.Gender.FEMALE).
                build();

        if(friends.contains(friend)){
            friends.remove(friend);
            friendsAdapter.notifyDataSetChanged();
            clearProfile();
            Toast.makeText(MainActivity.this, "Студент удален", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Студент не найден", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickProfilePhoto(View view) {
        Friend.Gender gender = radio_male.isChecked() ? Friend.Gender.MALE : Friend.Gender.FEMALE;
        int imageResource = Utils.getRandomPic(gender);
        profilePhotoView.setImageResource(imageResource);
        profilePhotoView.setTag(imageResource); //Кладем в тег profilePhotoView индентификатор установленной картинки, чтобы из тега можно было его вытащить в инт и установить в поле студента
    }

    public void onClickAddFriend(View view) {
        Button btn = (Button) view;

        if(profile.getVisibility() == View.VISIBLE){
            profile.setVisibility(View.GONE);
            btn.setText("Добавить студента");
        }
        else{
            profile.setVisibility(View.VISIBLE);
            btn.setText("Свернуть");
        }



    }
}