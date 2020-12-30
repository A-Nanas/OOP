package com.oop.task_3_2;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StudentRecordBook {
    private final Semester[] semesters;
    private final Discipline qualificationAssignment = new Discipline(DisciplineType.QUALIFICATION_ASSIGNMENT);
    private final List<List<DisciplineType>> disciplinesInSemesters = Arrays.asList(
            Arrays.asList(
                    DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS,
                    DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC,
                    DisciplineType.DECLARATIVE_PROGRAMMING,
                    DisciplineType.IMPERATIVE_PROGRAMMING,
                    DisciplineType.FOREIGN_LANGUAGE,
                    DisciplineType.FUNDAMENTALS_OF_SPEECH_CULTURE,
                    DisciplineType.HISTORY,
                    DisciplineType.PHYSICAL_CULTURE_FACULTATIVE,
                    DisciplineType.PHYSICAL_CULTURE,
                    DisciplineType.COMPUTING_PLATFORMS
            ),
            Arrays.asList(
                    DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS,
                    DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC,
                    DisciplineType.DECLARATIVE_PROGRAMMING,
                    DisciplineType.IMPERATIVE_PROGRAMMING,
                    DisciplineType.FOREIGN_LANGUAGE,
                    DisciplineType.PHYSICAL_CULTURE_FACULTATIVE,
                    DisciplineType.PHYSICAL_CULTURE,
                    DisciplineType.COMPUTING_PLATFORMS
            ),
            Arrays.asList(
                    DisciplineType.FOREIGN_LANGUAGE,
                    DisciplineType.PHYSICAL_CULTURE_FACULTATIVE,
                    DisciplineType.PHYSICAL_CULTURE,
                    DisciplineType.INTRODUCTION_TO_ARTIFICIAL_INTELLIGENCE,
                    DisciplineType.DIFFERENTIAL_EQUATIONS_AND_COMPLEX_ANALYSES,
                    DisciplineType.COMPUTATIONAL_MODELS,
                    DisciplineType.OBJECT_ORIENTED_PROGRAMMING,
                    DisciplineType.PROBABILITY_THEORY_AND_MATHEMATICAL_STATISTICS,
                    DisciplineType.OPERATING_SYSTEMS,
                    DisciplineType.GROUP_PROJECT
            ),
            Arrays.asList(
                    DisciplineType.PHYSICAL_CULTURE_FACULTATIVE,
                    DisciplineType.COMPUTATIONAL_MODELS,
                    DisciplineType.OBJECT_ORIENTED_PROGRAMMING,
                    DisciplineType.GROUP_PROJECT,
                    DisciplineType.INTRODUCTION_TO_ANALOG_ELECTRONICS_AND_MEASUREMENT_TECHNIQUES,
                    DisciplineType.INTRODUCTION_TO_COMPUTER_NETWORKS,
                    DisciplineType.BUSINESS_ENGLISH,
                    DisciplineType.PARALLELISM_THEORY,
                    DisciplineType.PROGRAMMABLE_MICROCONTROLLERS,
                    DisciplineType.PHILOSOPHY
            )
    );

    StudentRecordBook() {
        semesters = new Semester[disciplinesInSemesters.size()];
        for (int i = 0; i < disciplinesInSemesters.size(); i++) {
            semesters[i] = new Semester(disciplinesInSemesters.get(i));
        }
    }

    StudentRecordBook(List<Map<DisciplineType, Integer>> semesterGrades) {
        if (semesterGrades.size() > 4) {
            throw new IllegalArgumentException("size of grades list is bad");
        }
        semesters = new Semester[semesterGrades.size()];
        for (int i = 0; i < semesters.length; i++) {
            semesters[i] = new Semester(disciplinesInSemesters.get(i), semesterGrades.get(i));
        }
    }

    public void setQualificationAssignmentGrade(int grade) {
        qualificationAssignment.setGrade(grade);
    }

    public int getQualificationAssignmentGrade() {
        return qualificationAssignment.getGrade();
    }

    public boolean isQualificationAssignmentGradeSet() {
        return qualificationAssignment.isGradeSet();
    }

    public double getMeanGrade() {
        Supplier<Stream<Discipline>> passedDisciplinesSup = () -> Arrays.stream(semesters).flatMap(s -> s.disciplines.stream())
                .filter(Discipline::isGradeSet);
        long gradeCount = passedDisciplinesSup.get().count();
        int gradeSum = passedDisciplinesSup.get().map(Discipline::getGrade).reduce(0, Integer::sum);
        return (double)gradeSum / gradeCount;
    }

    public boolean checkForIncreasedScholarship(int semesterNumber) {
        int semesterIdx = semesterNumber - 1;
        if (semesterIdx < 0 || semesterIdx >= semesters.length) {
            throw new IllegalArgumentException("Bad semester number");
        }
        for (Discipline discipline: semesters[semesterIdx].disciplines) {
            if (!discipline.isGradeSet()) {
                return false;
            }
            if (discipline.getGrade() != 5) {
                return false;
            }
        }
        return true;
    }

    public boolean isRedDiplomaPossible() {
        int gradeCount = 0;
        int excellentGradeCount = 0;
        if (isQualificationAssignmentGradeSet()) {
            int qualificationAssignmentGrade = getQualificationAssignmentGrade();
            if (qualificationAssignmentGrade != 5) {
                return false;
            }
        }

        for (DisciplineType disciplineType: DisciplineType.values()) {
            Integer grade = getLastGradeOfDisciplineType(disciplineType);
            if (grade == null) {
                excellentGradeCount++; // assume a student CAN do their best
                gradeCount++;
                continue;
            }
            if (grade < 4) {
                return false;
            }
            if (grade == 5) {
                excellentGradeCount++;
            }
            gradeCount++;
        }
        return ((double) excellentGradeCount / gradeCount) >= 0.75;
    }

    private Integer getLastGradeOfDisciplineType(DisciplineType disciplineType) {
        List<Semester> semestersReversed = Arrays.asList(semesters);
        Collections.reverse(semestersReversed);
        Stream<Integer> gradesOfDiscipline = semestersReversed.stream()
                .flatMap(s -> s.disciplines.stream())
                .filter(s -> s.type == disciplineType)
                .map(Discipline::getGrade);
        return gradesOfDiscipline.findFirst().orElse(null);
    }

    private class Semester {
        public List<Discipline> disciplines;
        Semester(List<DisciplineType> disciplineTypes) {
            disciplines = new ArrayList<>();
            for (DisciplineType type: disciplineTypes) {
                disciplines.add(new Discipline(type));
            }
        }

        Semester(List<DisciplineType> disciplineTypes, Map<DisciplineType, Integer> gradesByDiscType) {
            disciplines = new ArrayList<>();
            for (int i = 0; i < disciplineTypes.size(); i++) {
                DisciplineType disciplineType = disciplineTypes.get(i);
                Integer grade = gradesByDiscType.get(disciplineType);
                if (grade != null) {
                    disciplines.add(new Discipline(disciplineType, grade));
                }
            }
        }
    }

    class Discipline {
        private DisciplineType type;
        private Integer grade;
        Discipline(DisciplineType discType, int grade) {
            if (isGradeInvalid(grade)) {
                throw new IllegalArgumentException("Grade can only be [2,5]");
            }
            type = discType;
            this.grade = grade;
        }

        Discipline(DisciplineType discType) {
            type = discType;
            grade = null;
        }
        public int getGrade() {
            if (grade == null) {
                throw new IllegalStateException("Grade was not initialised");
            }
            return grade;
        }

        public void setGrade(int newGrade) {
            if (isGradeInvalid(newGrade)) {
                throw new IllegalArgumentException("Grade can only be [2,5]");
            }
            grade = newGrade;
        }

        public void unsetGrade() {
            grade = null;
        }

        public boolean isGradeSet() {
            return (grade != null);
        }

        public DisciplineType getType() {
            return type;
        }

        private boolean isGradeInvalid(int someGrade) {
            return (someGrade > 5 || someGrade < 2);
        }
    }
}
