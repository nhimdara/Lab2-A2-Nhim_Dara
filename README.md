# Student Course Registration System (Lab 2)

A console-based student course registration application developed in Kotlin. This project provides an interactive command-line interface (CLI) to manage students, courses, and course enrollments with input validation and in-memory persistence.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Data Models](#data-models)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
  - [Option 1: Using IntelliJ IDEA (Recommended)](#option-1-using-intellij-idea-recommended)
  - [Option 2: Using the Command Line (kotlinc)](#option-2-using-the-command-line-kotlinc)
- [Application Usage & Walkthrough](#application-usage--walkthrough)
- [Pre-populated Data](#pre-populated-data)
- [Author](#author)

---

## 📖 Overview

The **Student Course Registration System** is designed to model university course management:
- Maintains a registry of students with their majors and optional email addresses.
- Maintains a course catalog with course IDs, course titles, and credit ratings.
- Manages enrollment relationships between students and courses while enforcing constraints (e.g. checking for existence and preventing duplicate enrollments).

---

## ✨ Features

1. **Register a Student**:
   - Captures Student ID, Name, Email (optional), and Major.
   - Handles nullable email gracefully (stores `null` when left blank).

2. **Create a Course**:
   - Captures Course ID, Course Name, and Credits.
   - Parses credits safely with numeric fallback.

3. **Enrol a Student in a Course**:
   - Validates that both the Student ID and Course ID exist in the system.
   - Prevents duplicate enrollments for the same student in the same course.

4. **View Students in a Course**:
   - Queries and lists all enrolled students for any specified course ID.

5. **View All Students**:
   - Formatted list of all registered students with fallback display for missing emails (`"No email"`).

6. **View All Courses**:
   - Lists all courses along with credit counts and real-time enrolled student counts.

7. **Interactive CLI Menu**:
   - Continuous menu loop with input validation for options 1 through 7.

---

## 🧱 Data Models

The data classes are defined in [`src/Models.kt`](src/Models.kt):

### `Student`
```kotlin
data class Student(
    val id: String,
    val name: String,
    val email: String?,
    val major: String
)
```

### `Course`
```kotlin
data class Course(
    val courseId: String,
    val courseName: String,
    val credits: Int
)
```

### `Enrollment`
```kotlin
data class Enrollment(
    val studentId: String,
    val courseId: String
)
```

---

## 📁 Project Structure

```text
Lab2-A2-Nhim_Dara/
├── .idea/                  # IntelliJ IDEA project settings
├── src/
│   ├── Main.kt             # Main entry point, menu loop, and CLI handler functions
│   └── Models.kt           # Data class models (Student, Course, Enrollment)
├── lab 2.iml               # IntelliJ module file
├── .gitignore              # Git ignore rules
└── README.md               # Project documentation
```

---

## ⚙️ Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher recommended (JDK 11+ compatible).
- **Kotlin**: Version 1.9+ (bundled with IntelliJ IDEA or installed separately via CLI).
- **IDE**: [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community or Ultimate).

---

## 🚀 How to Run

### Option 1: Using IntelliJ IDEA (Recommended)

1. Open **IntelliJ IDEA**.
2. Select **Open** and choose the project directory (`lab 2`).
3. Allow IntelliJ to configure the Kotlin SDK and project dependencies.
4. Navigate to [`src/Main.kt`](src/Main.kt).
5. Click the green **Run** icon next to `fun main()` or press `Shift + F10`.

---

### Option 2: Using the Command Line (kotlinc)

You can compile and run directly using the Kotlin compiler:

```bash
# Compile both Kotlin source files into an executable JAR
kotlinc src/Models.kt src/Main.kt -include-runtime -d CourseRegistration.jar

# Run the compiled JAR
java -jar CourseRegistration.jar
```

---

## 🖥️ Application Usage & Walkthrough

When started, the application presents the following menu:

```text
===== Course Registration =====
1. Register a student
2. Create a course
3. Enrol a student
4. View students in a course
5. View all students
6. View all courses
7. Exit
Choose (1-7):
```

### Sample Workflow

1. **View Default Courses**: Choose `6` to inspect pre-loaded courses and enrollment numbers.
2. **Register a New Student**: Choose `1` and follow the prompts:
   ```text
   --- Register a student ---
   Student id: ST003
   Name: John Doe
   Email (Enter to skip): john@example.com
   Major: Computer Science
   Saved: ST003 John Doe
   ```
3. **Enrol Student**: Choose `3` and provide the student ID (`ST003`) and course ID (`MBL201`).
4. **Check Course Roster**: Choose `4` and enter `MBL201` to view all students enrolled.
5. **Exit**: Choose `7` to terminate the program.

---

## 📚 Pre-populated Data

To facilitate testing upon launch, the application comes with the following pre-loaded data:

### Students
| ID | Name | Email | Major |
| :--- | :--- | :--- | :--- |
| `ST001` | Sok Dara | *None* | IT |
| `ST002` | Chan Lina | `lina@mail.com` | Data Science |

### Courses
| Course ID | Course Name | Credits |
| :--- | :--- | :---: |
| `MBL201` | Mobile Application Development | 3 |
| `CS202` | Data Structures & Algorithms | 3 |
| `DB203` | Advanced Database Systems | 3 |
| `UI204` | Mobile UI/UX Design | 2 |
| `NET205` | Computer Networks & Security | 3 |
| `SWE206` | Software Engineering Principles | 3 |

---

## 👤 Author

- **Name**: Nhim Dara
- **Repository**: [nhimdara/Lab2-A2-Nhim_Dara](https://github.com/nhimdara/Lab2-A2-Nhim_Dara)
