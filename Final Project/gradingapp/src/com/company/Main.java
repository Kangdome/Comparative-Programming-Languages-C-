// Alexander J King
// Comparative Programming Languages
// Final project
// Due4.29.20


package com.company;

import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Resultset;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;


public class Main {

    private static int i = 0;

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        getConnection(); //Ensure we're connected to the mysqlDB that we want and expect.
        creatTables(); // Create Tables
        System.out.println("Welcome to King Grading Tool.");
        boolean running = true;
        while (running == true) {
            System.out.println("MAIN MENU: Please make a selection from the following options: \n   1. Add new student, professor, " +
                    "assignment, or class. \n   2. View students, professors, assignments, or classes. " +
                    "\n   3. View final grades for students. " +
                    "\n   4.  Add/Drop classes, grade student work" +
                    "\n   0. exit");
            Connection con = getConnection();
            int selection = scan.nextInt();

            if (selection == 1) {
                System.out.println("Option 1.");
                post(); // post data to the database.
            } //end i = 1
            else if (selection == 2) {
                get();
            } else if (selection == 3) {
                System.out.println("Please enter the student ID you would like to check grades for: ");
                int sid = scan.nextInt();
                String fix = scan.nextLine();
                System.out.println("Please enter the class ID you would like to check grades for: ");
                int classid = scan.nextInt();
                fix = scan.nextLine();
                int aid = 0;
                double pointsEarned = 0;
                double pointsPossible = 0;
                PreparedStatement assignments = con.prepareStatement("SELECT assignment_id FROM t_assignments WHERE assignment_classid = ? ");
                assignments.setInt(1, classid);
                ResultSet ass = assignments.executeQuery();
                while (ass.next()) {
                    aid = ass.getInt("assignment_id");
                    PreparedStatement grade = con.prepareStatement("SELECT assignment_pearned, assignment_ppossible FROM t_studentassignment WHERE assignment_sid = ? and assignment_assid =?");
                    grade.setInt(1, sid);
                    grade.setInt(2, aid);
                    ResultSet gr = grade.executeQuery();
                    while (gr.next()) {
                        System.out.println("sid:" + sid + " aid: " + aid);
                        pointsEarned = pointsEarned + gr.getInt("assignment_pearned");
                        pointsPossible = pointsPossible + gr.getInt("assignment_ppossible");
                        System.out.println("Points earned Total: " + pointsEarned + " Points possible: " + pointsPossible + " Grade: " + pointsEarned / pointsPossible * 100 + "%");
                        if (pointsEarned / pointsPossible * 100 >= 90) {
                            System.out.println("You got an A");
                        } else if (pointsEarned / pointsPossible * 100 >= 80 && pointsEarned / pointsPossible * 100 <= 89) {
                            System.out.println("You got a B");
                        } else if (pointsEarned / pointsPossible * 100 >= 70 && pointsEarned / pointsPossible * 100 <= 79) {
                            System.out.println("You got a C");
                        } else if (pointsEarned / pointsPossible * 100 >= 60 && pointsEarned / pointsPossible * 100 <= 69) {
                            System.out.println("You got a D");
                        } else {
                            System.out.println("You got an F");
                        }
                    }
                }


//                String fname = "";
//                String lname = "";
//                int classid;
//                int classid1;
//                int classid2;
//                int classid3;
//                int classid4;
//
//
//                PreparedStatement getName = con.prepareStatement("Select *FROM t_students");
//                ResultSet names = getName.executeQuery();
//                while (names.next()) {
//                    fname = names.getString("student_fname");
//                    lname = names.getString("student_lname");
//                    sid = names.getInt("student_id");
//                    classid = names.getInt("student_classid");
//                    classid1 = names.getInt("student_classid1");
//                    classid2 = names.getInt("student_classid2");
//                    classid3 = names.getInt("student_classid3");
//                    classid4 = names.getInt("student_classid4");
//
//
//                    PreparedStatement assignments = con.prepareStatement("SELECT * FROM t_assignments WHERE assignment_classid = ?");
//                    assignments.setInt(1,classid);
//                    ResultSet ass = assignments.executeQuery();
//                    while (ass.next()) {
//
//
//                        PreparedStatement grading = con.prepareStatement("SELECT * FROM t_studentassignment WHERE assignment_sid = ? and ");
//                        ResultSet grades = grading.executeQuery();
//                        grading.setInt(1, sid);
//                        while (grades.next()) {
//                            grades.getInt("")
//                        }
//                    }
//
//                }
            } else if (selection == 4) {
                update();
            } else if (selection == 0) {
                System.out.println("Option 0. Exit program. \nThank you for using King Grading Tool. ♥ "); // my users are objectively the best in my opinion.
                running = false;
            } else if (selection == 9) {
                System.out.println("Welcome to the testing section!");

                PreparedStatement getLastAddedAssignmentID = con.prepareStatement("SELECT  MAX(assignment_id) FROM t_assignments");
                ResultSet lastAss = getLastAddedAssignmentID.executeQuery();
                int lastAddedAss = 0;
                while (lastAss.next()) {
                    lastAddedAss = lastAss.getInt(1);
                }
                System.out.println(lastAddedAss);


//                String classIDs = "3";
//                int classID = Integer.parseInt(classIDs);
//                Connection con = getConnection();
//                PreparedStatement getprofID = con.prepareStatement("SELECT  class_profid FROM t_classes WHERE class_id = ?");
//                getprofID.setInt(1, classID);
//                ResultSet profID = getprofID.executeQuery();
//                System.out.println("test line");
//                int professorID=0;
//                while (profID.next()){
//                    System.out.println(profID.getInt("class_profid"));
//                    professorID = profID.getInt("class_profid");
//                    System.out.println(professorID);
//                }
//                System.out.println("outside while: " + professorID);


//                System.out.println("int class id = " +classID);
//                PreparedStatement getInfo8 = con.prepareStatement("SELECT  student_id FROM t_students WHERE student_classid = ? or student_classid1 = ? or student_classid2 = ? or student_classid3 = ? or student_classid4 = ? ");
//                getInfo8.setInt(1, classID);
//                getInfo8.setInt(2, classID);
//                getInfo8.setInt(3, classID);
//                getInfo8.setInt(4, classID);
//                getInfo8.setInt(5, classID);
//                ResultSet testing = getInfo8.executeQuery();
//                int counter =0;
//                while (testing.next()){
//                    ArrayList<String> array01 = new ArrayList<String>();
//                    array01.add(testing.getString("student_id"));
//                    System.out.println("counter: " + counter);
//                    System.out.println(array01.get(0)+ " output");
//                    counter++;
//                }// end while loop
            } else {
                System.out.println("Invalid input.");
            }
        }//end while running


    }// end of main

//
//    public class Counter {
//        public int i = 0; // used to count so I do over verbose output information.
//    } //end of counter


    public static void creatTables() throws Exception {
        try { //taken from sql file i made. Make all the tables if they dont exist.
            Connection con = getConnection();
            PreparedStatement createAssignment = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `t_assignments` (\n" +
                            "  `assignment_id` int(8) NOT NULL AUTO_INCREMENT,\n" +
                            "  `assignment_classid` int(8) DEFAULT NULL,\n" +
                            "  `assignment_profid` int(8) DEFAULT NULL,\n" +
                            "  `assignment_type` int(1) DEFAULT NULL,\n" +
                            "  `assignment_points` double DEFAULT NULL,\n" +
                            "  `assignment_pointspossible` double DEFAULT NULL,\n" +
                            "  `assignment_studentid` int DEFAULT NULL,\n" +
                            "  `assignment_assigneddate` date DEFAULT NULL,\n" +
                            "  `assignment_duedate` date DEFAULT NULL,\n" +
                            "  `assignment_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`assignment_id`)\n" +
                            ") ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;");
            createAssignment.executeUpdate();

            PreparedStatement createClass = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `t_classes` (\n" +
                            "  `class_id` int(8) NOT NULL AUTO_INCREMENT,\n" +
                            "  `class_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
                            "  `class_credithours` int(1) DEFAULT NULL,\n" +
                            "  `class_profid` int(8) DEFAULT NULL,\n" +
                            "  `class_location` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
                            "  `class_startdate` date DEFAULT NULL,\n" +
                            "  `class_enddate` date DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`class_id`)\n" +
                            ") ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;");
            createClass.executeUpdate();
            PreparedStatement createProf = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `t_profs` (\n" +
                            "  `prof_id` int(8) NOT NULL AUTO_INCREMENT,\n" +
                            "  `prof_fname` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `prof_lname` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `prof_dob` date NOT NULL,\n" +
                            "  `prof_phone` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `prof_address` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `prof_email` varchar(80) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`prof_id`)\n" +
                            ") ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;");
            createProf.executeUpdate();
            PreparedStatement createStudent = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `t_students` (\n" +
                            "  `student_id` int(8) NOT NULL AUTO_INCREMENT,\n" +
                            "  `student_fname` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `student_lname` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `student_dob` date NOT NULL,\n" +
                            "  `student_address` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `student_phone` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,\n" +
                            "  `student_email` varchar(80) DEFAULT NULL,\n" +
                            "  `student_classid` int(80) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`student_id`)\n" +
                            ") ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;");
            createStudent.executeUpdate();

            PreparedStatement createSA = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `t_studentassignment` (\n" +
                            "  `assignmentID` int(8) NOT NULL AUTO_INCREMENT,\n" +
                            "  `assignment_sid` int(11) DEFAULT NULL,\n" +
                            "  `assignment_pid` int(11) DEFAULT NULL,\n" +
                            "  `assignmen_pearned` int(11) DEFAULT NULL,\n" +
                            "  `assignment_ppossible` int(11) DEFAULT NULL,\n" +
                            "  `assignment_assid` int(11) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`assignmentID`)\n" +
                            ") ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;");
            createSA.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Create Tables Complete!");
        }
    }// end of create tables

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver"; //most recent driver
            String url = "jdbc:mysql://localhost:3306/db_uni"; //The url where I want to look for my database.
            String uname = "root"; // the username credential I'm going to pass. (This is default)
            String password = ""; // Password credential (default)
            Class.forName(driver); // Class.forName (this is the driver from just above)
            Connection conn = DriverManager.getConnection(url, uname, password); // connect passing the url, uname, and pw from above
            if (i < 1) {
                System.out.println("Database Connection successful!"); // Tell user all is going well.
                i++;
            }
            return conn; // return the connection
        } catch (Exception e) {
            System.out.println(e);
        } // end try and error out if you fail to connect.
        return null;
    } // end connection


    public static void post() throws Exception {
        boolean running = true;
        while (running == true) {
            System.out.println("Which of the following would you like to add? \n     1. Student \n     2. Professor " +
                    "\n     3. Assignment \n     4. Class \n     0. Main Menu");
            Scanner scan = new Scanner(System.in);//scanner online
            int selection = scan.nextInt();
            if (selection == 1) {
                try {
                    Connection con = getConnection(); // connect
                    String fix = scan.nextLine();
                    System.out.println("Please enter your first name: "); //various scanners to get the needed inputs.
                    String fname = scan.nextLine();
                    System.out.println("Please enter your last name: ");
                    String lname = scan.nextLine();
                    System.out.println("Please input your date of birth yyyyMMdd EX: Augest 13th 1966 would be 19660813"); // nice formatting eh? It's functional. So making it nicer is a lower priority than finishing everything else.
                    String dob = scan.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // reformat to new pattern
                    Date parsedDate = format.parse(dob); // NEED TO PARSE USER INPUT -> LONG AND INPUT HERE
                    java.sql.Date date = new java.sql.Date(parsedDate.getTime()); //get time from above line for SQL formatting.
                    //  System.out.println(date); // previously used for verbose output testing.
                    System.out.println("Please enter your address: ");
                    String address = scan.nextLine();
                    System.out.println("Please enter your phone number: ");
                    String phone = scan.nextLine();
                    System.out.println("Please enter your email address: ");
                    String email = scan.nextLine();
                    System.out.println("You can sign up for upto 5 classes. Right now you must sign up for at least one. Later you can add addition classes from the Main Menu option 4.");
                    System.out.println("Please input a classID to sign up for: ");
                    int classid = scan.nextInt();


                    PreparedStatement postStudent = con.prepareStatement("INSERT INTO `t_students` (`student_id`, `student_fname`, `student_lname`, `student_dob`, `student_address`, `student_phone`, `student_email`, `student_classid`) VALUES\n" +
                            "(null, '" + fname + "', '" + lname + "', '" + date + "', '" + address + "', '" + phone + "', '" + email + "', '" + classid + "');"); // The formatting required for this was a nightmare. Copied from .sql file and then reworked to utilize variables. Copy this for other database inputs.
                    postStudent.executeUpdate();
                    System.out.println("Posted update!"); // Tell the user we did a thing
                } catch (Exception e) {
                    System.out.println(e); //Tell the user we failed and why.
                } finally {
                    System.out.println("INSERT COMPLETED"); // not actually redundant to the above.
                }
            }// end if selection ==1
            else if (selection == 2) {
                try { // literally the same as the above students - 1 field for classes id. Might actually include this later.
                    Connection con = getConnection(); // connect
                    String fix = scan.nextLine();
                    System.out.println("Please enter your first name: "); //various scanners to get the needed inputs.
                    String fname = scan.nextLine();
                    System.out.println("Please enter your last name: ");
                    String lname = scan.nextLine();
                    System.out.println("Please input your date of birth yyyyMMdd EX: Augest 13th 1966 would be 19660813"); // nice formatting eh?
                    String dob = scan.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // reformat to new pattern
                    Date parsedDate = format.parse(dob); // NEED TO PARSE USER INPUT -> LONG AND INPUT HERE
                    java.sql.Date date = new java.sql.Date(parsedDate.getTime()); //get time from above line for SQL formatting.
                    //  System.out.println(date); // previously used for verbose output testing.
                    System.out.println("Please enter your address: ");
                    String address = scan.nextLine();
                    System.out.println("Please enter your phone number: ");
                    String phone = scan.nextLine();
                    System.out.println("Please enter your email address: ");
                    String email = scan.nextLine();

                    PreparedStatement postProfessor = con.prepareStatement("INSERT INTO `t_profs` (`prof_id`, `prof_fname`, `prof_lname`, `prof_dob`, `prof_phone`, `prof_address`, `prof_email`) VALUES\n" +
                            "(null, '" + fname + "', '" + lname + "', '" + date + "', '" + phone + "', '" + address + "', '" + email + "');"); // The formatting required for this was a nightmare. Copied from .sql file and then reworked to utilize variables. Copy this for other database inputs.
                    postProfessor.executeUpdate();
                    System.out.println("Posted update!"); // Tell the user we did a thing
                } catch (Exception e) {
                    System.out.println(e); //Tell the user we failed and why.
                } finally {
                    return;
                }


            }// end else if ==2
            else if (selection == 3) {
                try {
                    Connection con = getConnection(); // connect
                    String fix = scan.nextLine();
                    System.out.println("Please enter the class ID: ");
                    int classID = scan.nextInt();
//                    fix = scan.nextLine();
                    System.out.println("Please enter the professor ID");
                    int professorID = scan.nextInt();
                    fix = scan.nextLine();
                    System.out.println("Please input the date the assignment is AVAILABLE yyyyMMdd EX: Augest 13th 1966 would be 19660813"); // nice formatting eh?
                    String dateAssigned = scan.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // reformat to new pattern
                    Date parsedDateAssigned = format.parse(dateAssigned); // NEED TO PARSE USER INPUT -> LONG AND INPUT HERE
                    java.sql.Date date0 = new java.sql.Date(parsedDateAssigned.getTime()); //get time from above line for SQL formatting.
                    System.out.println("Please input the date the assignment is DUE yyyyMMdd EX: Augest 13th 1966 would be 19660813"); // nice formatting eh?
                    String dateDue = scan.nextLine();
                    Date parsedDateDue = format.parse(dateDue); // NEED TO PARSE USER INPUT -> LONG AND INPUT HERE
                    java.sql.Date date1 = new java.sql.Date(parsedDateDue.getTime()); //get time from above line for SQL formatting.
                    System.out.println("Please enter the assignment type: \n    1. Normal Assignment \n     2. Test ");
                    int assignmentType = scan.nextInt();
                    System.out.println("Please enter the total possible points for this assignment: ");
                    double assignmentPointsPossible = scan.nextDouble();
                    String fix0 = scan.nextLine();
                    System.out.println("Please enter a name for this assignment: ");
                    String assignmentName = scan.nextLine();

                    PreparedStatement postAssignment = con.prepareStatement("INSERT INTO `t_assignments` (`assignment_id`, `assignment_classid`, `assignment_profid`, `assignment_studentid`, `assignment_type`, `assignment_points`, `assignment_pointspossible`, `assignment_assigneddate`, `assignment_duedate`, `assignment_name`) VALUES\n" +
                            "(null, '" + classID + "', '" + professorID + "', NULL,  '" + assignmentType + "', NULL, '" + assignmentPointsPossible + "', '" + date0 + "', '" + date1 + "', '" + assignmentName + "');"); // The formatting required for this was a nightmare. Copied from .sql file and then reworked to utilize variables. Copy this for other database inputs.
                    //----------------------------------------------------------------------------------------//
                    /*  Here I would like to auto-create a new addition in the database

                    What we already know: The professor ID, The class ID, Assignment Type, possible points, name---
                    * I want it create an addition for everyone in the class
                    Auto fill class id type possible points prof id
                    Incriment for each studentID create a new one.

                    * Select student ID from students WHERE classid classid1 etc  = assignment class*
                    *
                     */


                    postAssignment.executeUpdate(); //post assignment before distrib
                    // BREAK ------------------


                    PreparedStatement getprofID = con.prepareStatement("SELECT DISTINCT class_profid FROM t_classes WHERE class_id = ?"); // get the professor ID
                    getprofID.setInt(1, classID);
                    ResultSet profID = getprofID.executeQuery();
                    int proff = 0;
                    while (profID.next()) {
                        proff = profID.getInt("class_profid");
                    }// end GETTING PROFID PROFF


                    PreparedStatement getLastAddedAssignmentID = con.prepareStatement("SELECT  MAX(assignment_id) FROM t_assignments"); // get the last assignment ID by looking for the greatest value of assignmentID
                    ResultSet lastAss = getLastAddedAssignmentID.executeQuery();
                    int lastAddedAss = 0;
                    while (lastAss.next()) {
                        lastAddedAss = lastAss.getInt(1);
                    }
                    System.out.println(lastAddedAss);


                    // Check for all student IDs in a given class. Ensure to check all class fields. One student might have Class A in slot 1/5 while another has it in slot 2/5 etc...
                    PreparedStatement getStudentID = con.prepareStatement("SELECT DISTINCT student_id FROM t_students WHERE student_classid = ? or  student_classid2 = ? or  student_classid3 = ? or  student_classid4 = ? or  student_classid1 = ?");
                    getStudentID.setInt(1, classID);
                    getStudentID.setInt(2, classID);
                    getStudentID.setInt(3, classID);
                    getStudentID.setInt(4, classID);
                    getStudentID.setInt(5, classID);
                    int counter = 0;
                    int sID;
                    ResultSet sid = getStudentID.executeQuery();
                    while (sid.next()) { // the student IDs have multiple inputs so while there is a 'next student id' create a new SA field FOR EACH student
                        sID = sid.getInt("student_id");
                        PreparedStatement createSA = con.prepareStatement("INSERT INTO t_studentassignment (`assignmentID`, `assignment_sid`, `assignment_pid`, `assignment_pearned`, `assignment_ppossible`, `assignment_assid`, assignment_type) VALUES(null,'" + sID + "','" + proff + "', 0,'" + assignmentPointsPossible + "','" + lastAddedAss + "','" + assignmentType + "')");
                        createSA.executeUpdate();
//                        System.out.println("Automatically generated assignment for all in class!");

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }


                try {
                    Connection con = getConnection();
                    PreparedStatement statement = con.prepareStatement("SELECT student_fname, student_lname, student_id FROM t_students");

                    ResultSet result = statement.executeQuery();
                    ArrayList<String> array = new ArrayList<String>();

                    System.out.println("Posted update!"); // Tell the user we did a thing
                } catch (Exception e) {
                    System.out.println(e); //Tell the user we failed and why.
                } finally {
                    System.out.println("INSERT COMPLETED"); // not actually redundant to the above.
                }

            } else if (selection == 4) {
                try {
                    Connection con = getConnection(); // connect
                    String fix = scan.nextLine();
                    System.out.println("Please enter the professor ID: ");
                    int proffID = scan.nextInt();
                    System.out.println("Please enter the name of the class: ");
                    String fix2 = scan.nextLine();
                    String className = scan.nextLine();
                    System.out.println("Please enter the number of credit hours");
                    int classHours = scan.nextInt();
                    String fix1 = scan.nextLine();
                    System.out.println("Please input the date the class STARTS yyyyMMdd EX: Augest 13th 1966 would be 19660813"); // nice formatting eh?
                    String dateStart = scan.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // reformat to new pattern
                    Date parsedDateStart = format.parse(dateStart); // NEED TO PARSE USER INPUT -> LONG AND INPUT HERE
                    java.sql.Date date2 = new java.sql.Date(parsedDateStart.getTime()); //get time from above line for SQL formatting.
                    System.out.println("Please input the date the class ENDS yyyyMMdd EX: Augest 13th 1966 would be 19660813"); // nice formatting eh?
                    String dateEnd = scan.nextLine();
                    Date parsedDateEnd = format.parse(dateEnd); // NEED TO PARSE USER INPUT -> LONG AND INPUT HERE
                    java.sql.Date date3 = new java.sql.Date(parsedDateStart.getTime()); //get time from above line for SQL formatting.
                    System.out.println("Please enter a location for this class: ");
                    String classLocation = scan.nextLine();

                    PreparedStatement postClass = con.prepareStatement("INSERT INTO `t_classes` (`class_id`, `class_name`, `class_credithours`, `class_profid`, `class_location`, `class_startdate`, `class_enddate`) VALUES\n" +
                            "(null,'" + className + "', '" + classHours + "', '" + proffID + "','" + classLocation + "','" + date2 + "','" + date3 + "' );"); // The formatting required for this was a nightmare. Copied from .sql file and then reworked to utilize variables. Copy this for other database inputs.
                    postClass.executeUpdate();
                    System.out.println("Posted update!"); // Tell the user we did a thing
                } catch (Exception e) {
                    System.out.println(e); //Tell the user we failed and why.
                } finally {
                    System.out.println("INSERT COMPLETED"); // not actually redundant to the above.
                }

            } else if (selection == 0) {
                System.out.println("Option 0. Return to the main menu.");
                running = false;
            }
        }//end while running for adding new.
    }// end post

    public static ArrayList<String> get() throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running = true) {
            System.out.println("Which of the following would you like to view? \n     1. Students \n     2. Professors \n     3. Assignments \n     4. Classes \n     0. Main Menu");
            int selection = scan.nextInt();
            String fix = scan.nextLine();
            Connection con = getConnection();

            if (selection == 1) {
                try {

                    PreparedStatement getSInfo = con.prepareStatement("SELECT student_fname, student_lname, student_id FROM t_students");

                    ResultSet result = getSInfo.executeQuery();
                    ArrayList<String> array = new ArrayList<String>();
                    while (result.next()) {
                        System.out.print(result.getString("student_fname"));
                        System.out.print(" ");
                        System.out.print(result.getString("student_lname"));
                        System.out.print(" Student ID: ");
                        System.out.println(result.getString("student_id"));

                        array.add(result.getString("student_lname"));
                    }// end while loop
                    System.out.println("All records have been selected! \n");
                    return array;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }// end if == 1
            else if (selection == 2) {
                PreparedStatement getPInfo = con.prepareStatement("SELECT * FROM t_profs");
                ResultSet pInfo = getPInfo.executeQuery();
                while (pInfo.next()) {
                    System.out.println("ID: " + pInfo.getInt("prof_id") + " Name: " + pInfo.getString("prof_fname") + " " + pInfo.getString("prof_lname") + " " + pInfo.getString("prof_email"));

                }
            }//end else if ==2
            else if (selection == 3) {
                PreparedStatement getAInfo = con.prepareStatement("SELECT * FROM t_assignments");
                ResultSet aInfo = getAInfo.executeQuery();
                String cn = "";
                while (aInfo.next()) {
                    int aI = aInfo.getInt("assignment_classid");
                    PreparedStatement getClassName = con.prepareStatement("SELECT class_name FROM t_classes WHERE class_id = ?"); // I didnt need to do this but it's done now.
                    getClassName.setInt(1, aI);
                    ResultSet classN = getClassName.executeQuery();
//                    System.out.println("AI: "+ aI);
                    while (classN.next())
                        System.out.println("ID: " + aInfo.getString("assignment_id") + " Class:  " + classN.getString("class_name") + "\n Points possible: " + aInfo.getInt("assignment_pointspossible") + "Due: " + aInfo.getDate("assignment_duedate") + "\n");

                }
            } else if (selection == 4) {
                PreparedStatement getClassInfo = con.prepareStatement("SELECT * FROM t_classes");
                ResultSet classInfo = getClassInfo.executeQuery();
                int classID;
                int cch;
                String cnombre;
                String location;
                while (classInfo.next()) {
                    PreparedStatement getProfName = con.prepareStatement("SELECT prof_fname, prof_lname FROM t_profs WHERE prof_id = ?");
                    getProfName.setInt(1, classInfo.getInt("class_profid"));
                    cnombre = classInfo.getString("class_name");
                    classID = classInfo.getInt("class_id");
                    classInfo.getString("class_location");
                    cch = classInfo.getInt("class_credithours");
                    location = classInfo.getString("class_location");
                    System.out.println("ID: " + classID + " Class Name: " + cnombre + " Credit Hours: " + cch + " Location: " + location);
                }
            } else if (selection == 0) {
                System.out.println("Option 0. Return to the main menu");
                return null;
            } else {
                System.out.println("Invalid input.");
            }


        }//end while running
        return null;
    }


    public static void update() throws Exception {
        System.out.println("What would you like to update? \n 1. Add/Drop Classes \n 2.Grading \n 0. Main Menu ");
        Scanner scan = new Scanner(System.in);
        int selection = scan.nextInt();
        if (selection == 1) {
            System.out.println("Add or drop classes: \n What is your studentID #?");
            int studentID = scan.nextInt();
            System.out.println("Are you adding or dropping classes? \n 1. Adding \n 2. Dropping");
            int selection1 = scan.nextInt();
            if (selection1 == 1) {
                System.out.println("Add Classes:");
                try {
                    Connection con = getConnection();
                    PreparedStatement statement = con.prepareStatement("SELECT student_fname, student_lname, student_id, student_classid, student_classid1,student_classid2,student_classid3,student_classid4 FROM t_students" + " WHERE student_id= ?");
                    statement.setInt(1, studentID);

                    ResultSet result = statement.executeQuery();
                    ArrayList<String> array = new ArrayList<String>();
                    while (result.next()) {
                        System.out.print(result.getString("student_fname"));
                        System.out.print(" ");
                        System.out.print(result.getString("student_lname"));
                        System.out.print(" Student ID: ");
                        System.out.println(result.getString("student_id"));
                        System.out.print("Class IDs: ");
                        System.out.print(result.getString("student_classid"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid1"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid2"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid3"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid4"));
                        System.out.println(". ");
                        array.add(result.getString("student_lname"));
                        array.add(result.getString("student_fname"));
                        array.add(result.getString("student_id"));
                        array.add(result.getString("student_classid"));
                        array.add(result.getString("student_classid1"));
                        array.add(result.getString("student_classid2"));
                        array.add(result.getString("student_classid3"));
                        array.add(result.getString("student_classid4"));
                        String newClassName = "";
                        int position = 0;
                        if (array.get(3) == null) {
                            System.out.println("Class 1 is the next open");
                            newClassName = "student_classid1";
                            position = 1;
                            System.out.println("Please input the class ID you would like to sign up for in your class " + position + "/5 slot: ");
                            int newClassid = scan.nextInt();
                            PreparedStatement updateClassid1 = con.prepareStatement("UPDATE t_students" + " SET student_classid  = ?" + " WHERE student_id = ?");
                            // I really wanted to only make one of these and make "sudent_classidX" be a ? variable. But it broke when I tried to do this and I need to keep moving. I have multiple projects.
                            updateClassid1.setInt(1, newClassid);
                            updateClassid1.setInt(2, studentID);
                            updateClassid1.executeUpdate();
                        }
                        if (array.get(4) == null) {
                            System.out.println("Class 2 is the next open");
                            newClassName = "student_classid1";
                            position = 2;
                            System.out.println("Please input the class ID you would like to sign up for in your class " + position + "/5 slot: ");
                            int newClassid = scan.nextInt();
                            PreparedStatement updateClassid1 = con.prepareStatement("UPDATE t_students" + " SET student_classid1  = ?" + " WHERE student_id = ?");
                            // I really wanted to only make one of these and make "sudent_classidX" be a ? variable. But it broke when I tried to do this and I need to keep moving. I have multiple projects.
                            updateClassid1.setInt(1, newClassid);
                            updateClassid1.setInt(2, studentID);
                            updateClassid1.executeUpdate();
                        } else if (array.get(5) == null) {
                            System.out.println("Class 3 is the next open");
                            newClassName = "student_classid2";
                            position = 3;


                            System.out.println("Please input the class ID you would like to sign up for in your class " + position + "/5 slot: ");
                            int newClassid = scan.nextInt();
                            PreparedStatement updateClassid1 = con.prepareStatement("UPDATE t_students" + " SET student_classid2  = ?" + " WHERE student_id = ?");
                            updateClassid1.setInt(1, newClassid);
                            updateClassid1.setInt(2, studentID);
                            updateClassid1.executeUpdate();
                        } else if (array.get(6) == null) {
                            System.out.println("Class 4 is the next open");
                            newClassName = "student_classid3";
                            position = 4;


                            System.out.println("Please input the class ID you would like to sign up for in your class " + position + "/5 slot: ");
                            int newClassid = scan.nextInt();
                            PreparedStatement updateClassid1 = con.prepareStatement("UPDATE t_students" + " SET student_classid3  = ?" + " WHERE student_id = ?");
                            updateClassid1.setInt(1, newClassid);
                            updateClassid1.setInt(2, studentID);
                            updateClassid1.executeUpdate();
                        } else if (array.get(7) == null) {
                            System.out.println("Class 5 is last open");
                            newClassName = "student_classid4";
                            position = 5;


                            System.out.println("Please input the class ID you would like to sign up for in your class " + position + "/5 slot: ");
                            int newClassid = scan.nextInt();
                            PreparedStatement updateClassid1 = con.prepareStatement("UPDATE t_students" + " SET student_classid4  = ?" + " WHERE student_id = ?");
                            updateClassid1.setInt(1, newClassid);
                            updateClassid1.setInt(2, studentID);
                            updateClassid1.executeUpdate();
                        } else System.out.println("All classes are full!");

                    }// end try
                } catch (Exception e) {
                    System.out.println(e);
                }// end exception catch


                System.out.println("All records have been updated! \n");
                return;

            }


            if (selection1 == 2) {
                System.out.println("Drop classes: ");

                // --------------------------

                try {
                    Connection con = getConnection();
                    PreparedStatement statement = con.prepareStatement("SELECT student_fname, student_lname, student_id, student_classid, student_classid1,student_classid2,student_classid3,student_classid4 FROM t_students" + " WHERE student_id= ?");
                    statement.setInt(1, studentID);

                    ResultSet result = statement.executeQuery();
                    ArrayList<String> array = new ArrayList<String>();
                    while (result.next()) {
                        System.out.print(result.getString("student_fname"));
                        System.out.print(" ");
                        System.out.print(result.getString("student_lname"));
                        System.out.print(" Student ID: ");
                        System.out.println(result.getString("student_id"));
                        System.out.print("Class IDs: ");
                        System.out.print(result.getString("student_classid"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid1"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid2"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid3"));
                        System.out.print(", ");
                        System.out.print(result.getString("student_classid4"));
                        System.out.println(". ");
                        array.add(result.getString("student_lname"));
                        array.add(result.getString("student_fname"));
                        array.add(result.getString("student_id"));
                        array.add(result.getString("student_classid"));
                        array.add(result.getString("student_classid1"));
                        array.add(result.getString("student_classid2"));
                        array.add(result.getString("student_classid3"));
                        array.add(result.getString("student_classid4"));
                        System.out.println("Which class do you want to remove? \n 1. Fist \n 2. Second \n 3. Third \n 4. Fourth \n 5. Fifth ");
                        int dropClassID = scan.nextInt();
                        int position = 0;
                        if (dropClassID == 1) {
                            PreparedStatement deleteClassid1 = con.prepareStatement("UPDATE `t_students` SET `student_classid` = NULL WHERE `t_students`.`student_id` = ?");
                            deleteClassid1.setInt(1, studentID);
                            deleteClassid1.executeUpdate();
                        } else if (dropClassID == 2) {
                            PreparedStatement deleteClassid1 = con.prepareStatement("UPDATE `t_students` SET `student_classid1` = NULL WHERE `t_students`.`student_id` = ?");
                            deleteClassid1.setInt(1, studentID);
                            deleteClassid1.executeUpdate();

                        } else if (dropClassID == 3) {
                            PreparedStatement deleteClassid1 = con.prepareStatement("UPDATE `t_students` SET `student_classid2` = NULL WHERE `t_students`.`student_id` = ?");
                            deleteClassid1.setInt(1, studentID);
                            deleteClassid1.executeUpdate();
                        } else if (dropClassID == 4) {
                            PreparedStatement deleteClassid1 = con.prepareStatement("UPDATE `t_students` SET `student_classid3` = NULL WHERE `t_students`.`student_id` = ?");
                            deleteClassid1.setInt(1, studentID);
                            deleteClassid1.executeUpdate();
                        } else if (dropClassID == 5) {
                            PreparedStatement deleteClassid1 = con.prepareStatement("UPDATE `t_students` SET `student_classid4` = NULL WHERE `t_students`.`student_id` = ?");
                            deleteClassid1.setInt(1, studentID);
                            deleteClassid1.executeUpdate();
                        } else System.out.println("Invalid input");

                    }// end try
                } catch (Exception e) {
                    System.out.println(e);
                }// end exception catch


                System.out.println("All records have been updated! \n");

            }
        } else if (selection == 2) {

            int pe = 0; // points earned
            int pp = 0; // points possible
            int sid = 0; // student id
            int aaid = 0;// assignment id


            System.out.println("Grading");
            System.out.println("Please enter the Assignment ID you would like do to grading for:");
            int gradingAssID = scan.nextInt();
            String fix = scan.nextLine();
            System.out.println("Assignment ID: " + gradingAssID);
            Connection con = getConnection();


            PreparedStatement gradeClass = con.prepareStatement("SELECT assignment_pearned, assignment_ppossible, assignment_sid FROM t_studentassignment WHERE assignment_assid = ? and assignment_pearned = 0");
//            System.out.println("grade class loaded");
            gradeClass.setInt(1, gradingAssID);
            ResultSet toGrade = gradeClass.executeQuery();
            ArrayList<String> sas = new ArrayList<String>();
//            System.out.println("GradeClass");
            while (toGrade.next()) {
                pe = toGrade.getInt("assignment_pearned");
                pp = toGrade.getInt("assignment_ppossible");
                sid = toGrade.getInt("assignment_sid");
                sas.add(toGrade.getString("assignment_pearned"));
                sas.add(toGrade.getString("assignment_ppossible"));
                sas.add(toGrade.getString("assignment_sid"));


                PreparedStatement getClassName = con.prepareStatement("SELECT class_name FROM t_classes WHERE class_id =?");
                getClassName.setInt(1, gradingAssID);
                ResultSet className = getClassName.executeQuery();
                ArrayList<String> classn = new ArrayList<String>();
                String cn = "";
                while (className.next()) {
                    System.out.println("getClassName");
                    cn = className.getString("class_name");
                }

                PreparedStatement getAssignmentName = con.prepareStatement("Select  assignment_name FROM t_assignments WHERE assignment_id = ?");
                getAssignmentName.setInt(1, gradingAssID);
                ResultSet assName = getAssignmentName.executeQuery();
                String an = "";
                while (assName.next()) {
//                    System.out.println("getAssignmentname :");
                    an = assName.getString("assignment_name");
                }

                PreparedStatement getassAssID = con.prepareStatement("SELECT assignmentID FROM t_studentassignment WHERE assignment_sid = ? and assignment_assid =? ");
                getassAssID.setInt(1, sid);
                getassAssID.setInt(2, gradingAssID);
                ResultSet GAAN = getassAssID.executeQuery();
                while (GAAN.next()) {

//                    System.out.println("GANN");
                    aaid = GAAN.getInt("assignmentID");
                }


                PreparedStatement getSN = con.prepareStatement("SELECT student_fname, student_lname FROM t_students WHERE student_id = ?");
                getSN.setInt(1, sid);
                ResultSet getStudentName = getSN.executeQuery();
                ArrayList<String> gsn = new ArrayList<String>();
                String fname = ""; // first name
                String lname = ""; // last name
                while (getStudentName.next()) {
//                    System.out.println("getsn");
                    fname = getStudentName.getString("student_fname");
                    lname = getStudentName.getString("student_lname");
                    System.out.print(fname + " " + lname + " Student ID# " + sid + "\n" + cn + " - " + an + " |  Possible Points:" + pp + "\nPlease input the total number of points earned:\n");
                    pe = scan.nextInt();
                    if (pe > pp) {
                        System.out.println("Invalid input cannot have greater than 100%.");
                        return;
                    } else if (pe < 0) {
                        System.out.println("Invalid input cannot have less than 0.");
                        return;
                    }
                    //PreparedStatement postGrade = con.prepareStatement("UPDATE t_studentassignment SET assignment_pearned = ? WHERE assignment_assid = ? and assignment_sid =?" );
                    PreparedStatement postGrade = con.prepareStatement("UPDATE `t_studentassignment` SET `assignment_pearned` = ? WHERE `t_studentassignment`.`assignmentID` = ? and assignment_sid =?");
                    postGrade.setInt(1, pe);
                    postGrade.setInt(2, aaid);
                    postGrade.setInt(3, sid);
                    System.out.println("pe:" + pe);
                    postGrade.executeUpdate();
                    System.out.println("Execute update...");


                }// end while get name
            }// end while to grade
        } else if (selection == 0) {

        } else {
            System.out.println("Main Menu!");
            return;
        }
    } //end update


}
// end public class Main
