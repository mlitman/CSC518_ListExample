package com.example.awesomefat.csc518_listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.awesomefat.csc518_listexample.yelp.ImageViewThread;

import org.w3c.dom.Text;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        TextView nameTV = this.findViewById(R.id.nameTV);
        TextView phoneTV = this.findViewById(R.id.phoneTV);
        TextView addressTV = this.findViewById(R.id.addressTV);
        TextView reviewCountTV = this.findViewById(R.id.reviewCountTV);
        TextView ratingTV = this.findViewById(R.id.ratingTV);
        ImageView theImage = this.findViewById(R.id.theImage);

        ImageViewThread ivt = new ImageViewThread(Core.currSelectedRestaurant.getImageURL(), theImage);
        ivt.start();
        nameTV.setText(Core.currSelectedRestaurant.getName());
        phoneTV.setText(Core.currSelectedRestaurant.getPhoneNumber());
        addressTV.setText(Core.currSelectedRestaurant.getAddress());
        reviewCountTV.setText(Core.currSelectedRestaurant.getReviewCount());
        ratingTV.setText(Core.currSelectedRestaurant.getRating());

    }
}
