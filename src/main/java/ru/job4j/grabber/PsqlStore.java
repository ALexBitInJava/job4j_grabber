package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.username"),
                    cfg.getProperty("jdbc.password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement("INSERT INTO post (name, text, link, created) "
                + "VALUES (?, ?, ?, ?);")) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement("SELECT * FROM post;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(setPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement ps = cnn.prepareStatement("SELECT * FROM post WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                post = setPost(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    private Post setPost(ResultSet resultSet) throws SQLException {
        return new Post(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("link"),
                resultSet.getString("text"),
                resultSet.getTimestamp("created").toLocalDateTime());
    }
    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("post.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PsqlStore psqlStore = new PsqlStore(config)) {
            psqlStore.save(new Post("First post", "www.first-post.com", "web-recourse of the first post", LocalDateTime.now()));
            psqlStore.save(new Post("Second post", "www.second-post.com", "web-recourse of the second post", LocalDateTime.now()));
            psqlStore.save(new Post("Third post", "www.third-post.com", "web-recourse of the third post", LocalDateTime.now()));
            System.out.println(psqlStore.findById(1));
            psqlStore.getAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
