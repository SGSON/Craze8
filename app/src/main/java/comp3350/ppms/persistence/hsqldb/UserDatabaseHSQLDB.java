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

import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;

public class UserDatabaseHSQLDB extends HSQLDatabase implements UserDatabaseInterface{

    private final String dbPath;

    public UserDatabaseHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {

        final String UserID = rs.getString("userID");
        final String UserName = rs.getString("name");
        final String UserPassword = rs.getString("password");
        final ArrayList<String> CreatedProjectIDList = stringArrayConversion(rs.getArray("CreatedProjectIDList"));
        final ArrayList<String> LikedProjectIDList = stringArrayConversion(rs.getArray("LikedProjectIDList"));
        final ArrayList<String> MatchedProjectIDList = stringArrayConversion(rs.getArray("MatchedProjectList"));
        final ArrayList<String> UserCredentials = stringArrayConversion(rs.getArray("UserCredentials"));

        return new User(UserID, UserName, CreatedProjectIDList, LikedProjectIDList, MatchedProjectIDList, UserCredentials);

    }

    //@Override
    public ArrayList<User> getUserSequential() {
        final ArrayList<User> users = new ArrayList<>();
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
    public ArrayList<User> getUserInfo(User currentUser) {
        final ArrayList<User> users = new ArrayList<>();
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
    public User getUserByString(String userNickname) {
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


}
