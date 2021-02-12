package model.dao;

import model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract MedicalCardDao createMedicalCardDao();
    public abstract AssignmentDao getAssignmentDao();
    public abstract AssignmentNursehelperDao createAssignmentNursehelperDao();

    public static synchronized  DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}