package com.oop.task_3_2;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class StudentRecordBookTest {

    @Test
    public void meanGradeTest() {
        List<Map<DisciplineType, Integer>> myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 4);
                    put(DisciplineType.FOREIGN_LANGUAGE, 4);
                }},
                new HashMap(){{
                    put(DisciplineType.FOREIGN_LANGUAGE, 3);
                }}
        );
        StudentRecordBook studentRecordBook = new StudentRecordBook(myGrades);
        double myMeanGrade = studentRecordBook.getMeanGrade();
        Assert.assertEquals((double) (4 + 3 + 4) / 3, myMeanGrade, 0.05);
    }

    @Test
    public void meanGradeSecondTest() {
        List<Map<DisciplineType, Integer>> myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.HISTORY, 3);
                }}
        );
        StudentRecordBook studentRecordBook = new StudentRecordBook(myGrades);
        double myMeanGrade = studentRecordBook.getMeanGrade();
        Assert.assertEquals(3, myMeanGrade, 0.05);
    }

    @Test
    public void checkScholarshipTest() {

        List<Map<DisciplineType, Integer>> myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.FOREIGN_LANGUAGE, 2);
                }},
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 5);
                    put(DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, 5);
                    put(DisciplineType.DECLARATIVE_PROGRAMMING, 5);
                    put(DisciplineType.IMPERATIVE_PROGRAMMING, 5);
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.FUNDAMENTALS_OF_SPEECH_CULTURE, 5);
                    put(DisciplineType.HISTORY, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 5);
                    put(DisciplineType.COMPUTING_PLATFORMS, 5);
                }}
        );
        StudentRecordBook studentRecordBook = new StudentRecordBook(myGrades);
        Assert.assertTrue(studentRecordBook.checkForIncreasedScholarship(2));
        myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.FOREIGN_LANGUAGE, 3);
                }},
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 5);
                    put(DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, 4);
                    put(DisciplineType.DECLARATIVE_PROGRAMMING, 5);
                    put(DisciplineType.IMPERATIVE_PROGRAMMING, 5);
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.FUNDAMENTALS_OF_SPEECH_CULTURE, 5);
                    put(DisciplineType.HISTORY, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 5);
                    put(DisciplineType.COMPUTING_PLATFORMS, 5);
                }}
        );
        studentRecordBook = new StudentRecordBook(myGrades);
        Assert.assertFalse(studentRecordBook.checkForIncreasedScholarship(2));
    }

    @Test
    public void redDiplomaTest() {
        List<Map<DisciplineType, Integer>> myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 5);
                    put(DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, 5);
                    put(DisciplineType.DECLARATIVE_PROGRAMMING, 5);
                    put(DisciplineType.IMPERATIVE_PROGRAMMING, 4);
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.FUNDAMENTALS_OF_SPEECH_CULTURE, 5);
                    put(DisciplineType.HISTORY, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 5);
                    put(DisciplineType.COMPUTING_PLATFORMS, 5);
                }},
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 5);
                    put(DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, 5);
                    put(DisciplineType.DECLARATIVE_PROGRAMMING, 4);
                    put(DisciplineType.IMPERATIVE_PROGRAMMING, 5);
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 4);
                    put(DisciplineType.COMPUTING_PLATFORMS, 5);
                }},
                new HashMap(){{
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 5);
                    put(DisciplineType.INTRODUCTION_TO_ARTIFICIAL_INTELLIGENCE, 5);
                    put(DisciplineType.DIFFERENTIAL_EQUATIONS_AND_COMPLEX_ANALYSES, 5);
                    put(DisciplineType.COMPUTATIONAL_MODELS, 5);
                    put(DisciplineType.OBJECT_ORIENTED_PROGRAMMING, 5);
                    put(DisciplineType.PROBABILITY_THEORY_AND_MATHEMATICAL_STATISTICS, 5);
                    put(DisciplineType.OPERATING_SYSTEMS, 4);
                    put(DisciplineType.GROUP_PROJECT, 4);
                }},
                new HashMap(){{
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.INTRODUCTION_TO_COMPUTER_NETWORKS, 5);
                    put(DisciplineType.BUSINESS_ENGLISH, 4);
                    put(DisciplineType.PARALLELISM_THEORY, 5);
                    put(DisciplineType.PROGRAMMABLE_MICROCONTROLLERS, 5);
                    put(DisciplineType.PHILOSOPHY, 5);
                }}
        );

        StudentRecordBook studentRecordBook = new StudentRecordBook(myGrades);
        Assert.assertTrue(studentRecordBook.isRedDiplomaPossible());
        studentRecordBook = new StudentRecordBook(new ArrayList<>());
        Assert.assertTrue(studentRecordBook.isRedDiplomaPossible());

        myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.HISTORY, 3);
                }}
        );
        studentRecordBook = new StudentRecordBook(myGrades);
        Assert.assertFalse(studentRecordBook.isRedDiplomaPossible());
    }

    @Test
    public void redDiplomaFailByQualificationTest() {
        List<Map<DisciplineType, Integer>> myGrades = Arrays.asList(
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 5);
                    put(DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, 5);
                    put(DisciplineType.DECLARATIVE_PROGRAMMING, 5);
                    put(DisciplineType.IMPERATIVE_PROGRAMMING, 5);
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.FUNDAMENTALS_OF_SPEECH_CULTURE, 5);
                    put(DisciplineType.HISTORY, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 4);
                    put(DisciplineType.COMPUTING_PLATFORMS, 5);
                }},
                new HashMap(){{
                    put(DisciplineType.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, 5);
                    put(DisciplineType.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, 5);
                    put(DisciplineType.DECLARATIVE_PROGRAMMING, 4);
                    put(DisciplineType.IMPERATIVE_PROGRAMMING, 5);
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 4);
                    put(DisciplineType.COMPUTING_PLATFORMS, 5);
                }},
                new HashMap(){{
                    put(DisciplineType.FOREIGN_LANGUAGE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.PHYSICAL_CULTURE, 5);
                    put(DisciplineType.INTRODUCTION_TO_ARTIFICIAL_INTELLIGENCE, 5);
                    put(DisciplineType.DIFFERENTIAL_EQUATIONS_AND_COMPLEX_ANALYSES, 5);
                    put(DisciplineType.COMPUTATIONAL_MODELS, 5);
                    put(DisciplineType.OBJECT_ORIENTED_PROGRAMMING, 5);
                    put(DisciplineType.PROBABILITY_THEORY_AND_MATHEMATICAL_STATISTICS, 5);
                    put(DisciplineType.OPERATING_SYSTEMS, 4);
                    put(DisciplineType.GROUP_PROJECT, 4);
                }},
                new HashMap(){{
                    put(DisciplineType.PHYSICAL_CULTURE_FACULTATIVE, 5);
                    put(DisciplineType.COMPUTATIONAL_MODELS, 5);
                    put(DisciplineType.OBJECT_ORIENTED_PROGRAMMING, 5);
                    put(DisciplineType.GROUP_PROJECT, 5);
                    put(DisciplineType.INTRODUCTION_TO_ANALOG_ELECTRONICS_AND_MEASUREMENT_TECHNIQUES, 5);
                    put(DisciplineType.INTRODUCTION_TO_COMPUTER_NETWORKS, 5);
                    put(DisciplineType.BUSINESS_ENGLISH, 4);
                    put(DisciplineType.PARALLELISM_THEORY, 5);
                    put(DisciplineType.PROGRAMMABLE_MICROCONTROLLERS, 5);
                    put(DisciplineType.PHILOSOPHY, 5);
                }}
        );
        StudentRecordBook studentRecordBook = new StudentRecordBook(myGrades);
        studentRecordBook.setQualificationAssignmentGrade(4);
        Assert.assertFalse(studentRecordBook.isRedDiplomaPossible());
    }
}