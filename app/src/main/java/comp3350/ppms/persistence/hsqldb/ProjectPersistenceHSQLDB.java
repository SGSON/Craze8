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
        Project project= null;
        try
        {
            final PreparedStatement st = connection.prepareStatement("SELECT  * FROM projects WHERE projectID = ?");
            st.setString(1, ID.toString());

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
        try {
            final PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, ID);
            statement.setString(2, project.getProjectName());
            statement.setString(3, project.getProjectDescription());
            statement.setArray(4, connection.createArrayOf(
                    "varchar", project.getProjectCredentials().toArray()));
            statement.setArray(5, connection.createArrayOf(
                    "varchar", project.getInterestedUsers().toArray()));
            statement.setArray(6, connection.createArrayOf(
                    "varchar", project.getSelectedUsers().toArray()));

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

        final ArrayList<String> projCreds = stringArrayConversion
                (resultSet.getArray("PROJECT_CREDENTIALS"));
        final ArrayList<String> inUsers = stringArrayConversion(
                resultSet.getArray("INTERESTED_USERS"));
        final ArrayList<String> selUsers = stringArrayConversion(
                resultSet.getArray("SELECTED_USERS"));

        return new Project(UUID.fromString(projectID), projectName, projectDes, projCreds, inUsers, selUsers);
    }

    private ArrayList stringArrayConversion(Array input) {
        Object[] values;
        ArrayList<String> result = new ArrayList<>();

        if (input == null){
            return null;
        } else {
            try {
                values = (Object[]) input.getArray();
                for(int i = 0; i < values.length; i++) {
                    result.add(values[i].toString());
                }
                return result;
            } catch (SQLException e){
                throw new DatabaseException(e);
            }
        }
    }

}
