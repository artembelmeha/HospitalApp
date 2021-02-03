package model.dao.mapper;

import model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import static commands.Constants.*;

public class AssignmentMapper {
    public Assignment extractFromResultSet(ResultSet rs) throws SQLException {
        Assignment assignment = new Assignment();
        assignment.setId(rs.getLong(ID));
        assignment.setCurrentDiagnosis(rs.getString("current_diagnosis"));
        assignment.setDate(rs.getDate(DATE) == null ?
                null : rs.getDate(DATE).toLocalDate());
        assignment.setDoneTimes(rs.getInt("done_times"));
        assignment.setIsComplete(rs.getBoolean("is_complete"));
        assignment.setName(rs.getString("name"));
        assignment.setNotes(rs.getString("notes"));
        assignment.setQuantity(rs.getInt("quantity"));
        assignment.setType(AssignmentType.valueOf(rs.getString("type")));
        return assignment;
    }
}
