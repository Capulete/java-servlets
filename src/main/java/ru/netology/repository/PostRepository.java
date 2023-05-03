package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private AtomicLong currentID = new AtomicLong(0);
    private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return posts.containsKey(id) ? Optional.of(posts.get(id)) : Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(Long.valueOf(currentID.incrementAndGet()));
            posts.put(post.getId(), post);
        } else if (posts.containsKey(post.getId())) {
            posts.put(post.getId(), post);
        } else {
            throw new NotFoundException("ID не найден");
        }
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}