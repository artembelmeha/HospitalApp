package service;

import model.dao.AssignmentNursehelperDao;
import model.dao.DaoFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.beans.Transient;

import static org.mockito.Mockito.*;


public class AssignmentNurseHelperServiceTest {

    @BeforeClass
    public void intit() {

    }
    @Mock
    AssignmentNurshelperService testInstance;

    @Mock
    AssignmentNursehelperDao assignmentNursehelperDao;

    @Mock
    DaoFactory daoFactory;

    public static final long NURSE_ID = 5;
    public static final long ASSIGNMENT_ID = 2;

    @Test
    public void shouldAddNurseToAssignment(){
        when(daoFactory.createAssignmentNursehelperDao()).thenReturn(assignmentNursehelperDao);

        testInstance.addNurseToAssignment(NURSE_ID, ASSIGNMENT_ID);

        verify(assignmentNursehelperDao, times(1)).addUserToAssignment(NURSE_ID, ASSIGNMENT_ID);
    }

//       try (AssignmentNursehelperDao assignmentNursehelperDao =
//            DaoFactory.getInstance().createAssignmentNursehelperDao()) {
//        assignmentNursehelperDao.addUserToAssignment(nurseId, assignmentId);
//        LOGGER.info("User id#[" + nurseId + "] assigned to Assignment id [" + assignmentId + "]");
//    }

//    @Test
//    public void shouldCreateUser() {
//        when(registrationInfo.getEmail()).thenReturn(EMAIL);
//        when(registrationInfo.getPassword()).thenReturn(PASSWORD);
//        when(registrationInfo.getFirstName()).thenReturn(FIRST_NAME);
//        when(registrationInfo.getLastName()).thenReturn(LAST_NAME);
//        when(userRepository.save(any())).thenReturn(USER);
//
//        testInstance.create(registrationInfo);
//
//        verify(userRepository, times(1)).save(any());
//    }
}
