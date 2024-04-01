package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Problem;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class ProblemDao {
    private static final String INSERT_PROBLEM_SQL = "INSERT INTO room_problems (hotel_id, room_number, caption, description) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PROBLEM_SQL = "SELECT * FROM room_problems WHERE hotel_id = ? AND room_number = ? AND caption = ?";
    private static final String SELECT_ALL_PROBLEMS_SQL = "SELECT * FROM room_problems WHERE hotel_id = ? AND room_number = ?";
    private static final String UPDATE_PROBLEM_SQL = "UPDATE room_problems SET caption = ?, description = ? WHERE hotel_id = ? AND room_number = ? AND caption = ?";
    private static final String DELETE_PROBLEM_SQL = "DELETE FROM room_problems WHERE hotel_id = ? AND room_number = ? AND caption = ?";
    private static final String DELETE_ALL_PROBLEMS_SQL = "DELETE FROM room_problems WHERE hotel_id = ? AND room_number = ?";
    private final JdbcTemplate database;

    public ProblemDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertProblem(int hotelId, int roomNumber, Problem problem) {
        database.update(INSERT_PROBLEM_SQL, hotelId, roomNumber, problem.getCaption(), problem.getDescription());
    }

    public Problem getProblem(int hotelId, int roomNumber, String caption) {
        FieldMapper<Problem> mapper = new FieldMapper<>(database.getDataSource(), SELECT_PROBLEM_SQL, Problem.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "room_number"));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "caption"));
        return mapper.findObject(hotelId, roomNumber, caption);
    }
    
    public List<Problem> getProblemsWithRoom(int hotelId, int roomNumber) {
        FieldMapper<Problem> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ALL_PROBLEMS_SQL, Problem.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "room_number"));
        return mapper.execute(hotelId, roomNumber);
    }

    public void updateProblem(int hotelId, int roomNumber, String caption, Problem updatedProblem) {
        Problem originalProblem = getProblem(hotelId, roomNumber, caption);
        if (updatedProblem.getDescription() == null) updatedProblem.setDescription(originalProblem.getDescription());
        
        database.update(UPDATE_PROBLEM_SQL, hotelId, roomNumber, updatedProblem.getCaption(), updatedProblem.getDescription());
    }
    
    public void deleteProblem(int hotelId, int roomNumber, String caption) {
        database.update(DELETE_PROBLEM_SQL, hotelId, roomNumber, caption);
    }

    public void deleteAllProblems(int hotelId, int roomNumber) {
        database.update(DELETE_ALL_PROBLEMS_SQL, hotelId, roomNumber);
    }
}
