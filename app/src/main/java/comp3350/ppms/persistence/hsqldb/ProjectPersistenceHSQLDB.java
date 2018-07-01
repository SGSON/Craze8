package comp3350.ppms.persistence.hsqldb;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.domain.Project;
import comp3350.ppms.persistence.ProjectDatabaseInterface;

public class ProjectPersistenceHSQLDB implements ProjectDatabaseInterface {

    private final Connection connection;

    public ProjectPersistenceHSQLDB(final String dbPath) {
        try{
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath,"SA", "" );
        } catch(final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Project getProject(UUID ID) {
        try
        {
            final Statement st = connection.createStatement();
            final ResultSet rs = st.executeQuery("SELECT FROM projects FROM projects WHERE projectID = ?");

            final Project project = fromResultSet(rs);
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
    public void addProject(String ID, Project project, String projCreds, String intUsers, String selUsers) {
        try {
            final PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, ID);
            statement.setString(2, project.getProjectName());
            statement.setString(3, project.getProjectDescription());
            statement.setString(4, projCreds);
            statement.setString(5, intUsers);
            statement.setString(6, selUsers);

            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void removeProject(UUID ID) {
        try {
            final PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM projects WHERE projectID = ?");
            statement.setString(1, ID.toString()); //TODO: Project manager should handel conversion
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ArrayList<Project> getProjectSequential() {
        final ArrayList<Project> projects = new ArrayList<Project>();

        try
        {
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
        final String projectID = resultSet.getString("projectID");
        final String projectName = resultSet.getString("PROJECT_NAME");
        final String projectDes = resultSet.getString("PROJECT_DESCRIPTION");
        final Array projCreds = resultSet.getArray("PROJECT_CREDENTIALS");
        final Array inUsers = resultSet.getArray("INTERESTED_USERS");
        final Array selUsers = resultSet.getArray("SELECTED_USERS");

        //TODO: Further processing on the Array objects?
        ArrayList<String> credentials = (ArrayList<String>) projCreds.getArray();
        ArrayList<String> interestedUsers = (ArrayList<String>) inUsers.getArray();
        ArrayList<String> selectedUsers = (ArrayList<String>) selUsers.getArray();

        return new Project(UUID.fromString(projectID), projectName, projectDes, credentials, interestedUsers, selectedUsers);

    }
}
