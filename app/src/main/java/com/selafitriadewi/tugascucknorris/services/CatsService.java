package com.selafitriadewi.tugascucknorris.services;

import com.selafitriadewi.tugascucknorris.model.Cats;
import com.selafitriadewi.tugascucknorris.model.Cats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatsService {
    @GET("/floof/")
    Call<Cats> getRandomCats();
}
