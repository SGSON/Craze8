package comp3350.ppms.persistence.hsqldb;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.persistence.ProjectDatabaseInterface;

public class ProjectPersistenceHSQLDB extends HSQLDatabase implements ProjectDatabaseInterface {

    private final String dbPath;

    //Project Database Column Labels
    private static final String PROJECT_ID_COLUMN = "projectID";
    private static final String PROJECT_NAME_COLUMN = "PROJECT_NAME";
    private static final String PROJECT_OWNER_COLUMN = "PROJECT_OWNER";
    private static final String PROJECT_DESCRIPTION_COLUMN = "PROJECT_DESCRIPTION";
    private static final String PROJECT_CREDENTIALS_COLUMN = "PROJECT_CREDENTIALS";
    private static final String PROJECT_INTERESTED_USERS_COLUMN = "INTERESTED_USERS";
    private static final String PROJECT_SELECTED_COLUMN = "SELECTED_USERS";

    public ProjectPersistenceHSQLDB(final String path) {
        dbPath = path;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public Project getProject(String ID) {
        Project project= null;
        try (final Connection connection =  connection()){
            final PreparedStatement st = connection.prepareStatement("SELECT  * FROM projects WHERE projectID = ?");
            st.setString(1, ID);

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                project = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return project;
        }
        catch (final SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addProject(String ID, Project project) {
        try (final Connection connection = connection()){
            final PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, ID);
            statement.setString(2, project.getProjectName());
            statement.setString(3, project.getProjectOwner());
            statement.setString(4, project.getProjectDescription());
            statement.setArray(5, connection.createArrayOf(
                    "varchar", project.getProjectCredentials().toArray()));
            statement.setArray(6, connection.createArrayOf(
                    "varchar", project.getInterestedUsers().toArray()));
            statement.setArray(7, connection.createArrayOf(
                    "varchar", project.getSelectedUsers().toArray()));

            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateProject(Project project) {
        try (final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement("UPDATE projects SET PROJECT_NAME = ?," +
                    "PROJECT_DESCRIPTION = ?,  PROJECT_CREDENTIALS = ?, INTERESTED_USERS = ?, SELECTED_USERS = ? " +
                    "WHERE projectID = ?");
            st.setString(1, project.getProjectName());
            st.setString(2, project.getProjectDescription());
            st.setArray(3, c.createArrayOf("varchar", project.getProjectCredentials().toArray()));
            st.setArray(4, c.createArrayOf("varchar", project.getInterestedUsers().toArray()));
            st.setArray(5, c.createArrayOf("varchar", project.getSelectedUsers().toArray()));
            st.setString(6, project.getProjectID());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void removeProject(String ID) {
        try (final Connection connection = connection()){
            final PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM projects WHERE projectID = ?");
            statement.setString(1, ID.toString()); //TODO: Project manager should handel conversion
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Project> getProjectSequential() {
        final List<Project> projects = new ArrayList<Project>();

        try (final Connection connection = connection()){
            final Statement st = connection.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM projects");
            while (rs.next())
            {
                final Project project = fromResultSet(rs);
                projects.add(project);
            }
            rs.close();
            st.close();

            return projects;
        }
        catch (final SQLException e)
        {
            throw new DatabaseException(e);
        }
    }



    private Project fromResultSet(final ResultSet resultSet) throws SQLException {
        final String projectID = resultSet.getString(PROJECT_ID_COLUMN);
        final String projectName = resultSet.getString(PROJECT_NAME_COLUMN);
        final String projectOwner = resultSet.getString(PROJECT_OWNER_COLUMN);
        final String projectDes = resultSet.getString(PROJECT_DESCRIPTION_COLUMN);

        final List<String> projCreds = stringArrayConversion
                (resultSet.getArray(PROJECT_CREDENTIALS_COLUMN));
        final List<String> inUsers = stringArrayConversion(
                resultSet.getArray(PROJECT_INTERESTED_USERS_COLUMN));
        final List<String> selUsers = stringArrayConversion(
                resultSet.getArray(PROJECT_SELECTED_COLUMN));

        return new Project(projectID, projectName, projectOwner, projectDes, projCreds, inUsers, selUsers);
    }


}
