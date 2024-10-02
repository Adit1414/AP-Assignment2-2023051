# AP-Assignment2-2023051


## Overview

This project implements three key features:
1. *Generic Feedback System*: Students can provide feedback on completed courses using a generic class that can handle two types of feedback: Numeric ratings and Textual comments.
2. *Enhanced User Role Management*: A new user role, *TA (Teaching Assistant)*, is introduced, which inherits from the Student class and has additional functionalities related to assisting professors with grading.
3. *Robust Exception Handling*: Custom exception handling is added to manage invalid course registrations, login failures, and course drop deadline violations.

---

## 1. Generic Feedback System

### Concept
The Generic Feedback System uses *Generic Programming* to manage different types of feedback provided by students on completed courses.

### Solution
- *Generic Class for Feedback*: A class Feedback<T> was created to store feedback of different types, such as:
    - *Numeric Ratings*: Integer values like 1–5.
    - *Textual Comments*: Strings like "Great course!".

- *Implementation*:
    - Students can provide feedback after completing a course using the addFeedback method in the Student class.
    - The Feedback class is designed to accept any type of feedback, which is added to the course's feedbackList.
    - Professors can view all feedback provided by students for their courses.
    - Feedbacks are anonymous

### Key Classes
- Feedback: A generic class to store feedback.
- Student: A class where students can add feedback on completed courses.
- Course: Stores the feedback associated with each course.

---

## 2. Enhanced User Role Management with Object Class

### Concept
*Object-Oriented Programming* and *Inheritance* are used to introduce a new role: the *TA (Teaching Assistant)*, which extends the Student class with additional functionalities.

### Solution
- *TA Class*:
    - Inherits from the Student class.
    - Has access to student functionalities (course registration, viewing grades, etc.) and additional capabilities like managing grades for students.
    - A TA does *not* have full privileges of a professor, such as updating course details.

- *Role-Specific Functionalities*:
    - TA can view and manage student grades for a course, but they cannot modify course details.

### Key Classes
- TA: Inherits from Student and adds methods for assisting professors with grading.

---

## 3. Robust Exception Handling for Course Registration and Login

### Concept
*Exception Handling* is used to ensure that the system gracefully handles errors and abnormal conditions.

### Solution
- *Custom Exceptions*: Custom exception classes were created for different failure scenarios:
    - CourseFullException: Thrown when a student tries to register for a course that is already full.
    - InvalidLoginException: Thrown when a user attempts to log in with incorrect credentials.
    - DropDeadlinePassedException: Thrown when a student tries to drop a course after the deadline has passed.

- *Implementation*:
    - *Course Registration*: When a student tries to register for a course, a check is performed to see if the course has reached its maximum enrollment. If it has, a CourseFullException is thrown.
    - *Login System*: If a user provides incorrect credentials, the system throws an InvalidLoginException and prompts the user to retry.
    - *Course Drop*: A check is performed to ensure the course drop deadline has not passed. If the deadline has passed, a DropDeadlinePassedException is thrown.

### Key Classes
- CourseFullException: Handles course over-enrollment errors.
- InvalidLoginException: Handles invalid login attempts.
- DropDeadlinePassedException: Handles course drop failures after the deadline.

---

## Usage

### 1. Running the Program
- The program contains various classes for managing students, professors, courses, TAs, handling feedback etc.
- Each user type can interact with the system according to their role and privileges.
- When the program starts, it asks for login or signup. Some data has been initialized with seedData function.
- I have included two functions which test TA functionality and Feedback functionality automatically in the beginning of each run


### 2. Exception Handling
- The system uses custom exceptions to manage errors, and appropriate try-catch blocks are used to catch and handle these exceptions gracefully.

### 3. Providing Feedback
- After completing a course, a student can use the addFeedback() method to provide feedback. Feedback can be either numeric ratings or textual comments, and professors can later view this feedback.

### 4. Managing Roles
- Teaching Assistants (TAs) have been added to the system with specific roles. They inherit from the Student class but have added responsibilities of managing student grades.

---

## Assumptions
- A student can only be made into a TA by a professor of the course.
- The student must have 9 or 10 grade in the course in order to be eligible for TAship of that course.
- A student can add feedback only to those courses that they have completed.
- The student can add feedback however many times they want.
- Feedbacks are anonymous and hence, professors can only see the content of the feedback, not the student's details who left the feedback

---

### Name: Aditya Verma
### Roll Number: 2023051