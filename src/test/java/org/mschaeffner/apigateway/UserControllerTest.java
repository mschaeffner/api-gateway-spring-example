package org.mschaeffner.apigateway;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mschaeffner.apigateway.PostMatcher.isPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.mschaeffner.apigateway.domain.Post;
import org.mschaeffner.apigateway.domain.User;

public class UserControllerTest {

	private static final int USER_ID = 1;
	
	private UserController controller;

	private JsonPlaceholderService jsonPlaceholderService;

	@Before
	public void before() {
		jsonPlaceholderService = mock(JsonPlaceholderService.class);
		controller = new UserController(jsonPlaceholderService);
	}

	@Test
	public void testGet() throws Exception {

		User user = createUser(USER_ID, "Max Mustermann", "max@mustermann.de");
		Future<Optional<User>> futureUser = new TestFuture<>(Optional.of(user));
		when(jsonPlaceholderService.getUser(USER_ID)).thenReturn(futureUser);

		List<Post> posts = new ArrayList<>();
		posts.add(createPost(USER_ID, 1, "Title 1"));
		posts.add(createPost(USER_ID, 2, "Title 2"));
		posts.add(createPost(USER_ID, 3, "Title 3"));
		Future<List<Post>> futurePosts = new TestFuture<>(posts);
		when(jsonPlaceholderService.getPostsByUserId(USER_ID)).thenReturn(futurePosts);
		
		User result = controller.get(USER_ID);

		assertThat(result.getId(), is(1));
		assertThat(result.getName(), is("Max Mustermann"));
		assertThat(result.getEmail(), is("max@mustermann.de"));

		assertThat(result.getPosts(), hasSize(3));
		assertThat(result.getPosts().get(0), isPost(USER_ID, 1, "Title 1"));
		assertThat(result.getPosts().get(1), isPost(USER_ID, 2, "Title 2"));
		assertThat(result.getPosts().get(2), isPost(USER_ID, 3, "Title 3"));

	}

	@Test
	public void testGetButUserHasNoPosts() throws Exception {

		User user = createUser(USER_ID, "Max Mustermann", "max@mustermann.de");
		Future<Optional<User>> futureUser = new TestFuture<>(Optional.of(user));
		when(jsonPlaceholderService.getUser(USER_ID)).thenReturn(futureUser);

		List<Post> posts = Collections.emptyList();
		Future<List<Post>> futurePosts = new TestFuture<>(posts);
		when(jsonPlaceholderService.getPostsByUserId(USER_ID)).thenReturn(futurePosts);
		
		User result = controller.get(USER_ID);

		assertThat(result.getId(), is(1));
		assertThat(result.getName(), is("Max Mustermann"));
		assertThat(result.getEmail(), is("max@mustermann.de"));
		assertThat(result.getPosts(), is(empty()));
	}

	@Test(expected=UserNotFoundException.class)
	public void testGetButUserNotFound() throws Exception {
		
		Future<Optional<User>> futureUser = new TestFuture<>(Optional.empty());
		when(jsonPlaceholderService.getUser(USER_ID)).thenReturn(futureUser);

		controller.get(USER_ID);
	}

	private User createUser(int userId, String name, String email) {
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setEmail(email);
		return user;
	}

	private Post createPost(int userId, int id, String title) {
		Post post = new Post();
		post.setUserId(userId);
		post.setId(id);
		post.setTitle(title);
		return post;
	}
	
}
