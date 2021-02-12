package model.dao.mapper;

import exception.EntityNotFoundException;
import model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static commands.Constants.*;

public class AssignmentMapper {

    private static final String CURRENT_DIAGNOSIS = "current_diagnosis";
    private static final String DONE_TIMES = "done_times";
    private static final String IS_COMPLETE = "is_complete";
    private static final String NAME = "name";
    private static final String NOTES = "notes";
    private static final String QUANTITY = "quantity";
    private static final String TYPE = "type";

    public static Assignment extractAssignment(ResultSet resultSet, String message) throws SQLException {
        if (resultSet.next()) {
            return extractFromResultSet(resultSet);
        }
        throw new EntityNotFoundException(message);
    }

    public static List<Assignment> extractAssignments(ResultSet resultSet) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        while (resultSet.next()) {
            assignments.add(extractFromResultSet(resultSet));
        }
        return assignments;
    }

    public static Assignment extractFromResultSet(ResultSet rs) throws SQLException {
        Assignment assignment = new Assignment();
        assignment.setId(rs.getLong(ID));
        assignment.setCurrentDiagnosis(rs.getString(CURRENT_DIAGNOSIS));
        assignment.setDate(rs.getDate(DATE) == null ? null : rs.getDate(DATE).toLocalDate());
        assignment.setDoneTimes(rs.getInt(DONE_TIMES));
        assignment.setIsComplete(rs.getBoolean(IS_COMPLETE));
        assignment.setName(rs.getString(NAME));
        assignment.setNotes(rs.getString(NOTES));
        assignment.setQuantity(rs.getInt(QUANTITY));
        assignment.setType(AssignmentType.valueOf(rs.getString(TYPE)));
        return assignment;
    }
}
