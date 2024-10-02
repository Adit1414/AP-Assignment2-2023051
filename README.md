   # AP-Assignment2-2023051

# Generic Feedback System, Enhanced User Role Management, and Robust Exception Handling

## Overview

This project implements three key features:
1. *Generic Feedback System*: Students can provide feedback on completed courses using a generic class that can handle various types of feedback, such as numeric ratings and textual comments.
2. *Enhanced User Role Management*: A new user role, *Teaching Assistant (TA)*, is introduced, which inherits from the Student class and has additional functionalities related to assisting professors with grading.
3. *Robust Exception Handling*: Custom exception handling is added to manage invalid course registrations, login failures, and course drop deadline violations.

---

## 1. Generic Feedback System (20 marks)

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

### Key Classes
- Feedback<T>: A generic class to store feedback.
- Student: A class where students can add feedback on completed courses.
- Course: Stores the feedback associated with each course.

---

## 2. Enhanced User Role Management with Object Class (20 marks)

### Concept
*Object-Oriented Programming* and *Inheritance* are used to introduce a new role: the *Teaching Assistant (TA)*, which extends the Student class with additional functionalities.

### Solution
- *TA Class*:
    - Inherits from the Student class.
    - Has access to student functionalities (course registration, viewing grades, etc.) and additional capabilities like managing grades for students.
    - A TA does *not* have full privileges of a professor, such as updating course details.

- *Role-Specific Functionalities*:
    - TA can view and manage student grades for a course, but they cannot modify course details.

### Key Classes
- TA: Inherits from Student and adds methods for assisting professors with grading.
- Professor: Can view feedback and manage courses and grades.
- Admin: Manages overall system operations.

---

## 3. Robust Exception Handling for Course Registration and Login (20 marks)

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
- The program contains various classes for managing students, professors, courses, TAs, and handling feedback.
- Each user type can interact with the system according to their role and privileges.

### 2. Exception Handling
- The system uses custom exceptions to manage errors, and appropriate try-catch blocks are used to catch and handle these exceptions gracefully.

### 3. Providing Feedback
- After completing a course, a student can use the addFeedback() method to provide feedback. Feedback can be either numeric ratings or textual comments, and professors can later view this feedback.

### 4. Managing Roles
- Teaching Assistants (TAs) have been added to the system with specific roles. They inherit from the Student class but have added responsibilities like managing student grades.

---

## Key Highlights
- *Generic Programming*: Allows for flexible feedback storage and management.
- *Inheritance and Role Management*: The TA role is introduced using inheritance, extending the functionality of the Student class while limiting privileges compared to professors.
- *Custom Exception Handling*: Robust handling of common error scenarios, making the system more reliable and user-friendly.****