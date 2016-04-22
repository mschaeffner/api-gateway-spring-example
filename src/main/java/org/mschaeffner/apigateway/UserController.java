package org.mschaeffner.apigateway;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.mschaeffner.apigateway.domain.Post;
import org.mschaeffner.apigateway.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final JsonPlaceholderService jsonPlaceholderService;

	@Autowired
	public UserController(JsonPlaceholderService jsonPlaceholderService) {
		this.jsonPlaceholderService = jsonPlaceholderService;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public User get(@PathVariable("userId") int userId)
			throws IOException, InterruptedException, ExecutionException, UserNotFoundException {

		Future<Optional<User>> futureUser = jsonPlaceholderService.getUser(userId);
		Future<List<Post>> futurePosts = jsonPlaceholderService.getPostsByUserId(userId);

		Optional<User> optionalUser = futureUser.get();
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException();
		}

		List<Post> posts = futurePosts.get();

		User user = optionalUser.get();
		user.setPosts(posts);
		return user;
	}

}
