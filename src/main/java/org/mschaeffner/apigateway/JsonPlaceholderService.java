package org.mschaeffner.apigateway;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.mschaeffner.apigateway.domain.Post;
import org.mschaeffner.apigateway.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Service
public class JsonPlaceholderService {

	private final JsonPlaceholderApi api;

	@Autowired
	public JsonPlaceholderService(@Value("${JsonPlaceholderService.baseUrl}") String baseUrl) {
		Retrofit retrofit = new Retrofit.Builder() //
				.baseUrl(baseUrl) //
				.addConverterFactory(JacksonConverterFactory.create()) //
				.build(); //
		api = retrofit.create(JsonPlaceholderApi.class);
	}

	@Async
	public Future<Optional<User>> getUser(int id) throws IOException {
		User user = api.getUser(id).execute().body();
		Optional<User> result = Optional.ofNullable(user);
		return new AsyncResult<>(result);
	}

	@Async
	public Future<List<Post>> getPostsByUserId(int userId) throws IOException {
		List<Post> posts = api.getPostsByUserId(userId).execute().body();
		return new AsyncResult<>(posts);
	}

}
