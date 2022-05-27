package com.example.smtours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlaceAdd extends AppCompatActivity {

    Places places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_add);

        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        DatabaseReference dRef = DB.getReference().child("places").child("all");

        //places = new Places(1, "Hanging Bridge", "Rangamati, Chittagong", "308.9 KM", "By bus", "5000", "2 days 1 night", "Breakfast: Bread, Egg, Banana\nLunch: Beef Tehari, Salad, Soft Drinks\nDinner: Plain rice, Chicken curry, Fish curry, Dal\nAnd Snacks, Tea, Water", "Bangladesh");
        //dRef.push().setValue(places);




        //places = new Places(1,  "Hanging Bridge", "Rangamati, Chittagong", "308.9 KM", "By bus", "5000", "2 days 1 night", "Breakfast: Bread, Egg, Banana\nLunch: Beef Tehari, Salad, Soft Drinks\nDinner: Plain rice, Chicken curry, Fish curry, Dal\nAnd Snacks, Tea, Water", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Frangamati.jpg?alt=media&token=88109993-b0b5-49c3-a719-1a171f0f774a");
        //dRef.push().setValue(places);
        //places = new Places(2,  "Cox’s Bazar Sea Beach", "Cox’s Bazar, Chittagong", "398.3 KM", "By bus", "8000", "3 days 2 night", "Breakfast: Bread, Egg, Banana\nLunch: Beef Tehari, Salad, Soft Drinks\nDinner: Plain rice, Chicken curry, Fish curry, Dal\nAnd Snacks, Tea, Water", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fcox_bazar.jpg?alt=media&token=15059f96-e8ab-42f7-a9a1-dfd0c22027b1");
        //dRef.push().setValue(places);
        //places = new Places(3,  "Mangrove Forest", "Sundarban, Khulna", "265 KM", "By bus", "2500", "2 days 3 nights", " Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks \nAnd Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fsundarban.jpg?alt=media&token=d671fa29-3072-42fb-8c08-a79c5eb41727");
        //dRef.push().setValue(places);
        //places = new Places(4,  "Floating Guava Market", "Pirojpur, Barishal", "267 KM", "By bus", "2500", "2 days 3 nights", " Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks \nAnd Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fpirojpur.jpg?alt=media&token=07a7e911-51db-45fa-833e-f12539834739");
        //dRef.push().setValue(places);
        //places = new Places(5,  "Nafakhum Waterfall", "Bandarban, Chittagong", "265 KM", "By bus", "2500", "2 days 3 night", " Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks \nAnd Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fnafakhum.jpg?alt=media&token=38d0678b-8eed-4be7-a457-53940774abed");
        //dRef.push().setValue(places);
        //places = new Places(6,  "Paharpur", "Naogaon, Rajshahi", "260 KM", "By bus", "2500", "2 days 3 night", "Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks \nAnd Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fpaharpur.jpg?alt=media&token=fc4056f2-b5cc-40bf-a18d-0cfe6f7eec43");
        //dRef.push().setValue(places);
        //places = new Places(7,  "Sonargaon Panam Nagar", "Narayanganj, Dhaka", "35 KM", "By bus", "1000", "1 days 1 night", " Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks \nAnd Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fsonargaon.jpeg?alt=media&token=9b1949ca-e8f3-474d-9b27-3598b649de10");
        //dRef.push().setValue(places);
        //places = new Places(8,  "Shaat Gombuj Mosque", "Bagerhat,Khulna", "266 KM", "By bus", "3500", "2 days 1 night", " Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks\n And Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fbagerhat.jpg?alt=media&token=dfce6502-3f23-4555-8e5e-6c58f632f2e6");
        //dRef.push().setValue(places);
        //places = new Places(9,  "Srimangal", "Moulvibazar, Sylhet", "185 KM", "By bus", "5000", "2 days 1 night", "Breakfast: Bread, Egg, Banana\nLunch: Beef Tehari, Salad, Soft Drinks\nDinner: Plain rice, Chicken curry, Fish curry, Dal\nAnd Snacks, Tea, Water", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fsrimangal.jpg?alt=media&token=25cbf759-5808-4cd5-8e73-709547f98c5f");
        //dRef.push().setValue(places);
        //places = new Places(10, "Kuakata Sea Beach", "Potuakhali, Barishal", "202 KM", "By bus", "3000", "2 days 1 night", " Breakfast: Ruti-Dalvaji \nLunch: Polao-Chicken \nDinner: Nan-Kabab and Cold drinks \nAnd Snacks, Tea, Water ", "Bangladesh", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fkuakata_beach.jpg?alt=media&token=a3cf3990-5613-4c11-bb70-55b8a7d5439a");
        //dRef.push().setValue(places);
        //places = new Places(11, "Maasai Kara", "Kenya, Africa", "6605 KM", "By Airline", "120000", "3 days 3 nights", "Breakfast: Bread, Egg, Milk\nLunch: Meat,Fat\nDinner: Blood Honey,tree bark \nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fmasai_kara.jpeg?alt=media&token=d3f5be8e-88f6-4924-9c43-e319cb91ab61");
        //dRef.push().setValue(places);
        //places = new Places(12, "Cloud Forests", "Costa Rika", "16228 KM", "By Airline", "250000", "3 days 2 nights", "Breakfast: Bows, Rice and Beans\nLunch: Gallo Pinto,Casado \nDinner: ,Chifrijo ,Ceviche\nAnd Snacks, Tea,Coffie,Water", " World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fcosta_rica.jpeg?alt=media&token=82727d3c-76bc-4ed5-9d8f-77a7f7a5b225");
        //dRef.push().setValue(places);
        //places = new Places(13, "Kerala", "India", "1493 KM", "By Train", "50000", "3 days 2 nights", "Breakfast: Appam with Stew, Puttu & Kadala Curry\nLunch: Beef Fry,Kerala Prawn Curry ,Soft Drinks\nDinner: Ari Pathri and Chicken Curry\nAnd Snacks, Tea, Water", " World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fkerala.jpeg?alt=media&token=5b3c8bb5-c9f2-4675-aa20-42922a9aada0");
        //dRef.push().setValue(places);
        //places = new Places(14, "Phuket","Thailand", "3138 KM", "By Airline", "250000", "3 days 2 nights", "Breakfast: Tom Yum Goong, Pad Thai \nLunch: Som tam, Tom Kha gai, kao Phad\nDinner: Kuay Tiew,gai pad med ma muang\nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fphuket.jpg?alt=media&token=282a904a-03b2-4512-bec2-a9a94dee21d2");
        //dRef.push().setValue(places);
        //places = new Places(15, "Bali","Indonesia", "6535 KM", "By Airline", "250000", "3 days 2 nights", "Breakfast:Babi guling-Suckling Pig \nLunch: Nasi Campur  ,Soft Drinks\nDinner: sate-Skewer\nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fbali.jpg?alt=media&token=1cbf9441-98fd-4be2-b207-c74e37e58248");
        //dRef.push().setValue(places);
        //places = new Places(16, "Santorini","Greece", "6290 KM", "By Airline", "350000", "3 days 2 nights", "Breakfast: Fava me koukia,Greek , \nLunch: Roasted Greek lamb,Moussaka,Soft Drinks\nDinner: Keftes,Pomegranates\nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fsantorini.jpeg?alt=media&token=941e0fb7-89df-48f0-be5b-7f44daed9442");
        //dRef.push().setValue(places);
        //places = new Places(17, "Pyramids of Giza","Egypt,Missor", "5940 KM", "By Airline", "250000", "3 days 2 nights", "Breakfast:Ful medames, Molokhiya\nLunch: Fatta,Ta’meya ,Soft Drinks\nDinner:Alexandrian Liver Sandwich,Besarah \nAnd Snacks, Tea, Water", " World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fpyramid.jpg?alt=media&token=9c2b8917-0dab-4bd8-b241-90caf3fdafa9");
        //dRef.push().setValue(places);
        //places = new Places(18, "Florence","Italy", "7360 KM", "By Airline", "350000", "3 days 2 nights", "Breakfast:Caprese Salad with Pesto Sauce,Panzenella \nLunch: Mushroom Risotto,Margherita Pizza,Soft Drinks\nDinner: Focaccia Bread, Pasta Con Pomodoro E Basilico \nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fflorence.jpg?alt=media&token=a41b72de-889f-4ebe-bed4-cf3ad7eafdc2");
        //dRef.push().setValue(places);
        //places = new Places(19, "Sydney","Australia", "9071 KM", "By Airline", "390000", "3 days 2 nights", "Breakfast: Bacon and egg roll\nLunch: Jacket potato with filling, Fish and chips, Soft Drinks\nDinner: Avocado on toast, Bacon and egg roll\nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Fsydney.jpg?alt=media&token=341c622e-752c-4453-8e77-41a108809c6c");
        //dRef.push().setValue(places);
        //places = new Places(20, "Amsterdam", "Netherlands", "7633 KM", "By Airline", "250000", "3 days 2 nights", "Breakfast: Cod, poffertjes,Mint tea\nLunch:Satey,Oorlog French fries \nDinner: Chutney,Stroopwafel\nAnd Snacks, Tea, Water", "World", "https://firebasestorage.googleapis.com/v0/b/sm-tours-dcab8.appspot.com/o/place%2Famsterdam.jpg?alt=media&token=be7df53f-5f8a-4762-9a32-db8c5e58e562");
        //dRef.push().setValue(places);




    }
}