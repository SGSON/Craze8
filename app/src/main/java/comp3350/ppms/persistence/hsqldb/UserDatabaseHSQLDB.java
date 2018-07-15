package comp3350.ppms.persistence.hsqldb;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;

public class UserDatabaseHSQLDB extends HSQLDatabase implements UserDatabaseInterface{

    private final String dbPath;

    //Project Database Column Labels
    private static final String USER_ID_COLUMN = "userID";
    private static final String USER_NAME_COLUMN = "name";
    private static final String USER_PASSWORD_COLUMN = "password";
    private static final String USER_CREATED_PROJECTS_COLUMN = "CreatedProjectIDList";
    private static final String USER_LIKED_PROJECTS_COLUMN = "LikedProjectIDList";
    private static final String USER_MATCHED_PROJECTS_COLUMN = "MatchedProjectList";
    private static final String USER_CREDENTIALS_COLUMN = "UserCredentials";

    public UserDatabaseHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {

        final String UserID = rs.getString(USER_ID_COLUMN);
        final String UserName = rs.getString(USER_NAME_COLUMN);
        final String UserPassword = rs.getString(USER_PASSWORD_COLUMN);
        final List<String> CreatedProjectIDList = stringArrayConversion(rs.getArray(USER_CREATED_PROJECTS_COLUMN));
        final List<String> LikedProjectIDList = stringArrayConversion(rs.getArray(USER_LIKED_PROJECTS_COLUMN));
        final List<String> MatchedProjectIDList = stringArrayConversion(rs.getArray(USER_MATCHED_PROJECTS_COLUMN));
        final List<String> UserCredentials = stringArrayConversion(rs.getArray(USER_CREDENTIALS_COLUMN));

        return new User(UserID, UserName, UserPassword, CreatedProjectIDList, LikedProjectIDList, MatchedProjectIDList, UserCredentials);

    }

    //@Override
    public List<User> getUserSequential() {
        final List<User> users = new ArrayList<>();
        try (final Connection c = connection()){
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                final User user = fromResultSet(rs);
                users.add(user);
            }
            rs.close();
            st.close();

            return users;
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    //@Override
    public List<User> getUserInfo(User currentUser) {
        final List<User> users = new ArrayList<>();
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("SELECT * FROM users WHERE userID = ?");
            st.setString(1, currentUser.getUserID());

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final User user = fromResultSet(rs);
                users.add(user);
            }

            rs.close();
            st.close();

            return users;
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void insertUser(User currentUser) {
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, currentUser.getUserID());
            st.setString(2, currentUser.getUserNickName());
            st.setString(3, currentUser.getUserPassword());
            st.setArray(4, c.createArrayOf("varchar", currentUser.getCreatedProjectIDList().toArray()));
            st.setArray(5, c.createArrayOf("varchar", currentUser.getLikedProjectIDList().toArray()));
            st.setArray(6, c.createArrayOf("varchar", currentUser.getMatchedProjectIDList().toArray()));
            st.setArray(7, c.createArrayOf("varchar", currentUser.getUserCredentials().toArray()));
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    //@Override
    public void updateUser(User currentUser) {
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("UPDATE users SET name = ?,  password = ?,  CreatedProjectIDList = ?, LikedProjectIDList = ?, MatchedProjectList = ?, UserCredentials = ? WHERE userID = ?");
            st.setString(1, currentUser.getUserNickName());
            st.setString(2, currentUser.getUserPassword());
            st.setArray(3, c.createArrayOf("varchar", currentUser.getCreatedProjectIDList().toArray()));
            st.setArray(4, c.createArrayOf("varchar", currentUser.getLikedProjectIDList().toArray()));
            st.setArray(5, c.createArrayOf("varchar", currentUser.getMatchedProjectIDList().toArray()));
            st.setArray(6, c.createArrayOf("varchar", currentUser.getUserCredentials().toArray()));
            st.setString(7, currentUser.getUserID());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void deleteUser(User currentUser) {
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("DELETE FROM users WHERE userID = ?");
            st.setString(1, currentUser.getUserID());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User getUserByUserName(String userNickname) {
        User user= null;
        try (final Connection connection =  connection()){
            final PreparedStatement st = connection.prepareStatement("SELECT  * FROM users WHERE name = ?");
            st.setString(1, userNickname);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                user = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return user;
        }
        catch (final SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User getUserByID(String ID) {
        User user= null;
        try (final Connection connection =  connection()){
            final PreparedStatement st = connection.prepareStatement("SELECT  * FROM users WHERE userID = ?");
            st.setString(1, ID);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                user = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return user;
        }
        catch (final SQLException e)
        {
            throw new DatabaseException(e);
        }
    }


}
