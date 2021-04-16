package talky.csse.db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {


    private static Connection connection;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talky_db?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC", "root", "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static User getUser(String email){

        User user = null;
        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url")
                );
            }

            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static boolean addUser(User user){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(email, password, full_name, birth_date, picture_url)" +
                    " VALUES (?, ?, ?, ?, ?)"
                    );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFull_name());
            statement.setString(4, user.getBirthday());
            statement.setString(5, user.getPictureUrl());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }



    public static boolean addPost(Post post){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO posts(author_id, title, short_content, content, post_date)" +
                    " VALUES (?, ?, ?, ?, ?)"
            );
            statement.setLong(1, post.getUser().getId());
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getShort_content());
            statement.setString(4, post.getContent());
            statement.setTimestamp(5, post.getPostDate());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }


    public static boolean saveUser(User user){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement(""+
                    "UPDATE users SET  full_name=?, birth_date=?, picture_url=? " +
                    "WHERE id = ?");


            statement.setString(1, user.getFull_name());
            statement.setString(2, user.getBirthday());
            statement.setString(3, user.getPictureUrl());
            statement.setLong(4,user.getId());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }


    public static boolean updatePassword(User user){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement(""+
                    "UPDATE users SET  password = ? " +
                    "WHERE id = ?");



            statement.setString(1, user.getPassword());
            statement.setLong(2,user.getId());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }

    public static ArrayList<Post> getAllPosts(){
        ArrayList<Post>posts = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT p.id, p.title, p.short_content, p.content, p.post_date,  p.author_id,  u.email,  u.password, u.full_name, u.birth_date, u.picture_url " +
                    "FROM posts p " +
                    "INNER JOIN users u ON p.author_id = u.id " +
                    "");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                posts.add(
                        new Post(
                                resultSet.getLong("id"),
                                new User(
                                        resultSet.getLong("author_id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("birth_date"),
                                        resultSet.getString("picture_url")
                                        ),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return posts;
    }


    public static Post getPost(Long id){
        Post post = null;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT p.id, p.title, p.short_content, p.content, p.post_date,  p.author_id,  u.email,  u.password, u.full_name, u.birth_date, u.picture_url " +
                    "FROM posts p " +
                    "INNER JOIN users u ON p.author_id = u.id " +
                    "WHERE p.id = ? " +
                    "");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()){

                   post =    new Post(
                                resultSet.getLong("id"),
                                new User(
                                        resultSet.getLong("author_id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("birth_date"),
                                        resultSet.getString("picture_url")
                                ),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date")
                        );

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return post;
    }


    public static ArrayList<Post> getAllPostsByUser(Long id){
        ArrayList<Post>posts = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT p.id, p.title, p.short_content, p.content, p.post_date,  p.author_id,  u.email,  u.password, u.full_name, u.birth_date, u.picture_url " +
                    "FROM posts p " +
                    "INNER JOIN users u ON p.author_id = u.id " +
                    "WHERE u.id = ? " +
                    "");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                posts.add(
                        new Post(
                                resultSet.getLong("id"),
                                new User(
                                        resultSet.getLong("author_id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("birth_date"),
                                        resultSet.getString("picture_url")
                                ),
                                resultSet.getString("title"),
                                resultSet.getString("short_content"),
                                resultSet.getString("content"),
                                resultSet.getTimestamp("post_date")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return posts;
    }


    public static ArrayList<User> getAllFriends(User user){
        ArrayList<User>friends = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT u1.id, u1.email, u1.password, u1.full_name, u1.birth_date, u1.picture_url " +
                    "FROM friends f " +
                    "INNER JOIN users u ON f.user_id = u.id " +
                    "INNER JOIN users u1 ON f.friend_id = u1.id " +
                    "WHERE u.id = ? " +
                    "");


            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                        friends.add(
                                new User(
                                        resultSet.getLong("id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("birth_date"),
                                        resultSet.getString("picture_url")
                                )
                        );



            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return friends;
    }


    public static ArrayList<Long> getAllFrId(User user){
        ArrayList<Long>ids = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT u1.id " +
                    "FROM friends f " +
                    "INNER JOIN users u ON f.user_id = u.id " +
                    "INNER JOIN users u1 ON f.friend_id = u1.id " +
                    "WHERE u.id = ? " +
                    "");


            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                ids.add(resultSet.getLong("id"));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return ids;
    }


    public static ArrayList<User> getSearchUsers(String search, User user){
        ArrayList<User>users = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users " +
                    "WHERE full_name LIKE ? " +
                    "EXCEPT " +
                    "SELECT * FROM users " +
                    "WHERE id = ? " +
                    "");


            statement.setString(1, search+'%');
            statement.setLong(2, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("picture_url")
                        )
                );



            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }




    public static boolean addRequest(FriendRequest friendRequest){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO friends_requests(user_id, request_sender_id, sent_time)" +
                    " VALUES (?, ?, ?)"
            );
            statement.setLong(1, friendRequest.getUser_id());
            statement.setLong(2, friendRequest.getRequest_sender_id());
            statement.setTimestamp(3, friendRequest.getSent_time());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }



    public static boolean addFriend(Friend friend){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO friends(user_id, friend_id, added_time)" +
                    " VALUES (?, ?, ?)"
            );
            statement.setLong(1, friend.getUser_id());
            statement.setLong(2, friend.getFriend_id());
            statement.setTimestamp(3, friend.getAdded_time());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }


    public static ArrayList<User> getAllFrRequests(User user){
        ArrayList<User>friends = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT u1.id, u1.email, u1.password, u1.full_name, u1.birth_date, u1.picture_url " +
                    "FROM friends_requests f " +
                    "INNER JOIN users u ON f.user_id = u.id " +
                    "INNER JOIN users u1 ON f.request_sender_id = u1.id " +
                    "WHERE u.id = ? " +
                    "");


            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                friends.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("picture_url")
                        )
                );



            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return friends;
    }

    public static boolean deleteRequest(Long sender, Long me){

        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement(""+
                    "DELETE FROM friends_requests WHERE request_sender_id = ? AND user_id = ? ");

            statement.setLong(1, sender);
            statement.setLong(2, me);

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows >0;
    }


    public static ArrayList<Long> getAllMyRequests(User user){
        ArrayList<Long>friends = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT u.id " +
                    "FROM friends_requests f " +
                    "INNER JOIN users u ON f.user_id = u.id " +
                    "INNER JOIN users u1 ON f.request_sender_id = u1.id " +
                    "WHERE u1.id = ? " +
                    "");


            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                friends.add(
                        resultSet.getLong("id")
                );



            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return friends;
    }

    public static boolean addMessage(Message message){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO messages (chat_id, user_id, sender_id, message_text, read_by_receiver, sent_date) " +
                    " VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setLong(1, message.getChatId());
            statement.setLong(2, message.getUserId());
            statement.setLong(3, message.getSenderId());
            statement.setString(4, message.getMessage_text());
            statement.setBoolean(5, message.getRead());
            statement.setTimestamp(6, message.getSent_date());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }



    public static Long addChat(Chat chat){
        Long row = 0L;

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO chats (user_id, opponent_user_id, created_date, latest_message_text, latest_message_time) " +
                    " VALUES (?, ?, ?, ?, ?); ", Statement.RETURN_GENERATED_KEYS
            );

            statement.setLong(1, chat.getUser().getId());
            statement.setLong(2, chat.getOpponent().getId());
            statement.setTimestamp(3, chat.getCreatedDate());
            statement.setString(4, chat.getLatestMessage());
            statement.setTimestamp(5, chat.getLatestMessageTime());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                row = generatedKeys.getLong(1);
            }



            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row;

    }



    public static Chat getChat(Long user_id, Long opponent_id){

        Chat chat = null;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.opponent_user_id, c.created_date, c.latest_message_text, c.latest_message_time, u.email AS u_em, u.password AS u_pass, u.full_name AS u_name, u.birth_date AS u_date, u.picture_url AS u_pic, u1.email AS u1_em, u1.password AS u1_pass, u1.full_name AS u1_name, u1.birth_date AS u1_date, u1.picture_url AS u1_pic  " +
                    "FROM chats c " +
                    "INNER JOIN users u ON c.user_id = u.id " +
                    "INNER JOIN users u1 ON c.opponent_user_id = u1.id " +
                    "WHERE user_id = ? AND opponent_user_id = ? " +
                    "");
            statement.setLong(1,user_id);
            statement.setLong(2,opponent_id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                chat = new Chat(
                        resultSet.getLong("id"),
                        new User(
                                resultSet.getLong("user_id"),
                                resultSet.getString("u_em"),
                                resultSet.getString("u_pass"),
                                resultSet.getString("u_name"),
                                resultSet.getString("u_date"),
                                resultSet.getString("u_pic")
                        ),
                        new User(
                                resultSet.getLong("opponent_user_id"),
                                resultSet.getString("u1_em"),
                                resultSet.getString("u1_pass"),
                                resultSet.getString("u1_name"),
                                resultSet.getString("u1_date"),
                                resultSet.getString("u1_pic")
                        ),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time")
                );
            }

            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return chat;
    }


    public static boolean saveChat(Chat chat){
        int row = 0;

        try{
            PreparedStatement statement = connection.prepareStatement(""+
                    "UPDATE chats SET  latest_message_text=?, latest_message_time=? " +
                    "WHERE id = ?");


            statement.setString(1, chat.getLatestMessage());
            statement.setTimestamp(2, chat.getLatestMessageTime());
            statement.setLong(3, chat.getId());


            row = statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return row>0;

    }


    public static ArrayList<Long> getnotReadMessages(Long user_id){

        ArrayList<Long>chats = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT chat_id FROM messages WHERE user_id = ? AND read_by_receiver = 0 ");
            statement.setLong(1,user_id);


            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
               chats.add(resultSet.getLong("chat_id"));
            }

            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return chats;
    }

    public static boolean deleteFriend(Long friend, Long me){

        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement(""+
                    "DELETE FROM friends WHERE friend_id = ? AND user_id = ? ");

            statement.setLong(1, friend);
            statement.setLong(2, me);

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows >0;
    }


    public static ArrayList<Chat> getAllChats(Long user_id){

        ArrayList<Chat>chats = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.opponent_user_id, c.created_date, c.latest_message_text, c.latest_message_time, u.email AS u_em, u.password AS u_pass, u.full_name AS u_name, u.birth_date AS u_date, u.picture_url AS u_pic, u1.email AS u1_em, u1.password AS u1_pass, u1.full_name AS u1_name, u1.birth_date AS u1_date, u1.picture_url AS u1_pic  " +
                    "FROM chats c " +
                    "INNER JOIN users u ON c.user_id = u.id " +
                    "INNER JOIN users u1 ON c.opponent_user_id = u1.id " +
                    "WHERE user_id = ? OR opponent_user_id = ? " +
                    "");
            statement.setLong(1,user_id);
            statement.setLong(2,user_id);


            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                chats.add(new Chat(
                            resultSet.getLong("id"),
                            new User(
                                    resultSet.getLong("user_id"),
                                    resultSet.getString("u_em"),
                                    resultSet.getString("u_pass"),
                                    resultSet.getString("u_name"),
                                    resultSet.getString("u_date"),
                                    resultSet.getString("u_pic")
                            ),
                            new User(
                                    resultSet.getLong("opponent_user_id"),
                                    resultSet.getString("u1_em"),
                                    resultSet.getString("u1_pass"),
                                    resultSet.getString("u1_name"),
                                    resultSet.getString("u1_date"),
                                    resultSet.getString("u1_pic")
                            ),
                            resultSet.getTimestamp("created_date"),
                            resultSet.getString("latest_message_text"),
                            resultSet.getTimestamp("latest_message_time")
                    )
                );
            }

            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return chats;
    }


    public static ArrayList<Chat> getSearchChats(String search, User user){
        ArrayList<Chat>chats = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.opponent_user_id, c.created_date, c.latest_message_text, c.latest_message_time, u.email AS u_em, u.password AS u_pass, u.full_name AS u_name, u.birth_date AS u_date, u.picture_url AS u_pic, u1.email AS u1_em, u1.password AS u1_pass, u1.full_name AS u1_name, u1.birth_date AS u1_date, u1.picture_url AS u1_pic  " +
                    "FROM chats c " +
                    "INNER JOIN users u ON c.user_id = u.id " +
                    "INNER JOIN users u1 ON c.opponent_user_id = u1.id " +
                    "WHERE u1_name LIKE ? AND (user_id = ? OR opponent_user_id = ?)" +
                    "");


            statement.setString(1, search+'%');
            statement.setLong(2, user.getId());
            statement.setLong(3, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                chats.add(new Chat(
                                resultSet.getLong("id"),
                                new User(
                                        resultSet.getLong("user_id"),
                                        resultSet.getString("u_em"),
                                        resultSet.getString("u_pass"),
                                        resultSet.getString("u_name"),
                                        resultSet.getString("u_date"),
                                        resultSet.getString("u_pic")
                                ),
                                new User(
                                        resultSet.getLong("opponent_id"),
                                        resultSet.getString("u1_em"),
                                        resultSet.getString("u1_pass"),
                                        resultSet.getString("u1_name"),
                                        resultSet.getString("u1_date"),
                                        resultSet.getString("u1_pic")
                                ),
                                resultSet.getTimestamp("created_date"),
                                resultSet.getString("latest_message_text"),
                                resultSet.getTimestamp("latest_message_time")
                        )
                );



            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return chats;
    }


    public static boolean makeRead(Long chatid, Long user_id){

        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement(""+
                    "UPDATE messages SET read_by_receiver = ? " +
                    "WHERE chat_id = ? AND user_id = ? ");

            statement.setBoolean(1, true);
            statement.setLong(2, chatid);
            statement.setLong(3, user_id);


            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows >0;
    }


    public static ArrayList<Message> getAllMessages(Long chat_id){

        ArrayList<Message>messages = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE chat_id = ? ORDER BY sent_date DESC ");
            statement.setLong(1,chat_id);


            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                messages.add(
                        new Message(
                                resultSet.getLong("id"),
                                resultSet.getLong("chat_id"),
                                resultSet.getLong("user_id"),
                                resultSet.getLong("sender_id"),
                                resultSet.getString("message_text"),
                                resultSet.getBoolean("read_by_receiver"),
                                resultSet.getTimestamp("sent_date")

                        )
                );
            }

            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return messages;
    }

    public static Chat getChat( Long id){

        Chat chat = null;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.opponent_user_id, c.created_date, c.latest_message_text, c.latest_message_time, u.email AS u_em, u.password AS u_pass, u.full_name AS u_name, u.birth_date AS u_date, u.picture_url AS u_pic, u1.email AS u1_em, u1.password AS u1_pass, u1.full_name AS u1_name, u1.birth_date AS u1_date, u1.picture_url AS u1_pic  " +
                    "FROM chats c " +
                    "INNER JOIN users u ON c.user_id = u.id " +
                    "INNER JOIN users u1 ON c.opponent_user_id = u1.id " +
                    "WHERE c.id = ?  " +
                    "");
            statement.setLong(1,id);


            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                chat = new Chat(
                        resultSet.getLong("id"),
                        new User(
                                resultSet.getLong("user_id"),
                                resultSet.getString("u_em"),
                                resultSet.getString("u_pass"),
                                resultSet.getString("u_name"),
                                resultSet.getString("u_date"),
                                resultSet.getString("u_pic")
                        ),
                        new User(
                                resultSet.getLong("opponent_user_id"),
                                resultSet.getString("u1_em"),
                                resultSet.getString("u1_pass"),
                                resultSet.getString("u1_name"),
                                resultSet.getString("u1_date"),
                                resultSet.getString("u1_pic")
                        ),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time")
                );
            }

            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return chat;
    }






}
