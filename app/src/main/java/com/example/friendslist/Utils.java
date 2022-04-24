package com.example.friendslist;

import java.util.Random;

public class Utils {

    public static int getRandomPic(Friend.Gender gender) {

        int randomInt = new Random().nextInt(3);

        switch (randomInt) {
            case 0:
                return gender.equals(Friend.Gender.MALE) ? R.drawable.male_1 : R.drawable.female_1;
            case 1:
                return gender.equals(Friend.Gender.MALE) ? R.drawable.male_2 : R.drawable.female_2;
            case 2:
                return gender.equals(Friend.Gender.MALE) ? R.drawable.male_3 : R.drawable.female_3;
            default: return R.drawable.male_1;
        }
    }
}
