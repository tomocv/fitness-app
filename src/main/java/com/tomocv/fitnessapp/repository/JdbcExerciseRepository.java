package com.tomocv.fitnessapp.repository;

import com.tomocv.fitnessapp.domain.Exercise;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcExerciseRepository implements ExerciseRepository {

    private static final String TABLE_NAME = "exercise";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_ALL = "SELECT * FROM exercise";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert exerciseInserter;

    public JdbcExerciseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.exerciseInserter = new SimpleJdbcInsert(jdbc)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }

    @Override
    public Set<Exercise> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToExercise));
    }

    @Override
    public Optional<Exercise> save(Exercise exercise) {
        try {
            exercise.setId(saveExerciseDetails(exercise));
            return Optional.of(exercise);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private Exercise mapRowToExercise(ResultSet rs, int rowNum) throws SQLException {
        return new Exercise(
                rs.getLong("id"),
                rs.getString("muscle_group"),
                rs.getString("exercise_name"),
                rs.getInt("number_of_reps"),
                rs.getFloat("weight"),
                rs.getString("description")
        );
    }

    private long saveExerciseDetails(Exercise exercise) {
        Map<String, Object> values = new HashMap<>();

        values.put("muscle_group", exercise.getMuscleGroup());
        values.put("exercise_name", exercise.getExerciseName());
        values.put("number_of_reps", exercise.getNumberOfReps());
        values.put("weight", exercise.getWeight());
        values.put("description", exercise.getDescription());

        return exerciseInserter.executeAndReturnKey(values).longValue();
    }
}
