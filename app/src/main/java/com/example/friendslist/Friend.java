package com.example.friendslist;


public class Friend {

    private String firstName;
    private String surName;
    private Gender gender;
    private int imageId;


    public Friend(){
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return surName;
    }

    public int getImageId() {
        return imageId;
    }

    public Gender getGender() {
        return gender;
    }

    enum Gender{MALE, FEMALE, NONE}

    public String isStudentFilled(){

        if(firstName.isEmpty()) {
            return "Имя не заполнено";
        }
        else if(surName.isEmpty()){
            return "Фамилия не заполнена";
        }
        else if(imageId == 0){
            return "Изображение не прикреплено";
        }
        else{
            return "OK";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Friend friend = (Friend) obj;
        return firstName.equals(friend.firstName)
                && surName.equals(friend.surName)
                && gender.equals(friend.gender);
    }

    public static class Builder{

        private final Friend friend;

        public Builder(){
            friend = new Friend();
            friend.firstName = "default";
            friend.surName = "default";
            friend.gender = Gender.NONE;
            friend.imageId = 0;
        }

        public Builder withName(String name){
            friend.firstName = name;
            return this;
        }

        public Builder withSurname(String surName){
            friend.surName = surName;
            return this;
        }

        public Builder withGender(Gender gender){
            friend.gender = gender;
            return this;
        }

        public Builder withImageId(int imageId){
            friend.imageId = imageId;
            return this;
        }

        public Friend build(){
            return friend;
        }
    }

}
