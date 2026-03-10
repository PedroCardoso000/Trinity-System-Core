package com.trinity.manneger_control.interfaces;

public interface DashboardInterface {

    long countAllActiveStudents(Long academicId);

    long countAllActiveBranchess(Long academicId);

    long countAllLessonsToday(Long academicId);

    long countAllMonthlyPresences(Long academicId);

    long countAllMonthlyBirthdays(Long academicId);
    
}
