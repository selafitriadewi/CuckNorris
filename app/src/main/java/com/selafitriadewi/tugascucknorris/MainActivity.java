package com.selafitriadewi.tugascucknorris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.selafitriadewi.tugascucknorris.generator.ServiceGenerator;
import com.selafitriadewi.tugascucknorris.model.Cats;
import com.selafitriadewi.tugascucknorris.model.Cats;
import com.selafitriadewi.tugascucknorris.services.CatsService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private ImageView iconImage;
    private CatsService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconImage = findViewById(R.id.image_icon);
        service = ServiceGenerator.createService(CatsService.class);

        Button moreButton = findViewById(R.id.button_more);
        moreCat();

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreCat();
            }
        });
    }
    private void moreCat() {
        Call<Cats> catsResponse = service.getRandomCats();
        catsResponse.enqueue(new Callback<Cats>() {
            @Override
            public void onResponse(Call<Cats> call, Response<Cats> response) {
                Cats cats = response.body();
                Picasso.get().load(cats.getImage()).into(iconImage);
            }

            @Override
            public void onFailure(Call<Cats> call, Throwable t) {
                Log.e(TAG, t.toString());
                String message = "Failed to get more joke, please check your connection.";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
