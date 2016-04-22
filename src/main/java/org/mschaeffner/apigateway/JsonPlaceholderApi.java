package org.mschaeffner.apigateway;

import java.util.List;

import org.mschaeffner.apigateway.domain.Post;
import org.mschaeffner.apigateway.domain.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {

	@GET("/users/{id}")
	Call<User> getUser(@Path("id") int id);

	@GET("/posts")
	Call<List<Post>> getPostsByUserId(@Query("userId") int userId);

}
