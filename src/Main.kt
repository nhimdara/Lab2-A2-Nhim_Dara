/**
 * Lab 2: Student Course Registration System
 *
 * Step 0 Note:
 * Replace the course list below with at least 6 courses from your own study programme
 * (real course names and real credit values, with at least 2 unique to your group).
 */

fun main() {
    // Memory storage for students, courses, and enrollments
    val students = mutableListOf<Student>(
         Student("ST001", "Sok Dara", null, "IT"),
         Student("ST002", "Chan Lina", "lina@mail.com", "Data Science")
    )

    // Pre-populate with at least 6 courses from your study programme (Step 0)
    val courses = mutableListOf<Course>(
        Course("MBL201", "Mobile Application Development", 3),
        Course("CS202", "Data Structures & Algorithms", 3),
        Course("DB203", "Advanced Database Systems", 3),
        Course("UI204", "Mobile UI/UX Design", 2),
        Course("NET205", "Computer Networks & Security", 3),
        Course("SWE206", "Software Engineering Principles", 3)
    )

    val enrollments = mutableListOf<Enrollment>()

    var running = true

    while (running) {
        printMenu()
        print("Choose (1-7): ")
        val choice = readlnOrNull()?.trim()

        when (choice) {
            "1" -> registerStudent(students)
            "2" -> createCourse(courses)
            "3" -> enrolStudent(students, courses, enrollments)
            "4" -> viewStudentsInCourse(courses, students, enrollments)
            "5" -> viewAllStudents(students)
            "6" -> viewAllCourses (courses, enrollments)
            "7" -> {
                println("Goodbye!")
                running = false
            }
            else -> println("Invalid option. Please choose a number between 1 and 7.\n")
        }
    }
}

fun viewAllStudents(students: MutableList<Student>) {
    println("\n--- All students ---")
    if (students.isEmpty()) {
        println("No students registered yet.")
        return
    }

    students.forEach { student ->
        println("${student.id} | ${student.name} | ${student.email ?: "No email"} | ${student.major}")
    }
    println()
}

fun viewAllCourses(courses: List<Course>, enrollments: List<Enrollment>) {
    println("\n--- All courses ---")
    if (courses.isEmpty()) {
        println("No courses created yet.")
        return
    }

    courses.forEach { course ->
        val studentCount = enrollments.count { it.courseId == course.courseId }
        println("${course.courseId} | ${course.courseName} | ${course.credits} credits | Enrolled: $studentCount")
    }
    println()
}

fun viewStudentsInCourse(
    courses: MutableList<Course>,
    students: MutableList<Student>,
    enrollments: MutableList<Enrollment>
) {
    println("\n--- Students in a course ---")
    print("Course id: ")
    val courseId = readlnOrNull()?.trim().orEmpty()

    val course = courses.find { it.courseId == courseId }
    if (course == null) {
        println("Course not found: $courseId\n")
        return
    }

    val enrolledStudents = enrollments
        .filter { it.courseId == courseId }
        .mapNotNull { enrollment -> students.find { it.id == enrollment.studentId } }

    if (enrolledStudents.isEmpty()) {
        println("No students are enrolled in ${course.courseName}.")
        return
    }

    println("Students enrolled in ${course.courseName}:")
    enrolledStudents.forEach { student ->
        println("- ${student.id} | ${student.name} | ${student.major}")
    }
    println()
}

/**
 * Displays the main menu using string templates
 */
fun printMenu() {
    val menu = """
        |===== Course Registration =====
        |1. Register a student
        |2. Create a course
        |3. Enrol a student
        |4. View students in a course
        |5. View all students
        |6. View all courses
        |7. Exit
    """.trimMargin()
    println(menu)
}

/**
 * Option 1: Register a student
 * Prompts user for id, name, email (optional), and major.
 * Stores null if email is left blank.
 */
fun registerStudent(students: MutableList<Student>) {
    println("\n--- Register a student ---")
    print("Student id: ")
    val id = readlnOrNull()?.trim().orEmpty()

    print("Name: ")
    val name = readlnOrNull()?.trim().orEmpty()

    print("Email (Enter to skip): ")
    val emailInput = readlnOrNull()?.trim()
    val email = if (emailInput.isNullOrEmpty()) null else emailInput

    print("Major: ")
    val major = readlnOrNull()?.trim().orEmpty()

    val student = Student(id = id, name = name, email = email, major = major)
    students.add(student)
    println("Saved: ${student.id} ${student.name}\n")
}

/**
 * Option 2: Create a course
 * Prompts user for courseId, courseName, and credits.
 */
fun createCourse(courses: MutableList<Course>) {
    println("\n--- Create a course ---")
    print("Course id: ")
    val courseId = readlnOrNull()?.trim().orEmpty()

    print("Course name: ")
    val courseName = readlnOrNull()?.trim().orEmpty()

    print("Credits: ")
    val creditsInput = readlnOrNull()?.trim()
    val credits = creditsInput?.toIntOrNull() ?: 0

    val course = Course(courseId = courseId, courseName = courseName, credits = credits)
    courses.add(course)
    println("Saved: ${course.courseId} ${course.courseName}\n")
}

/**
 * Option 3: Enrol a student in a course
 * Checks that both studentId and courseId exist before building an Enrollment.
 */
fun enrolStudent(
    students: List<Student>,
    courses: List<Course>,
    enrollments: MutableList<Enrollment>
) {
    println("\n--- Enrol a student ---")
    print("Student id: ")
    val studentId = readlnOrNull()?.trim().orEmpty()

    print("Course id: ")
    val courseId = readlnOrNull()?.trim().orEmpty()

    val student = students.find { it.id == studentId }
    val course = courses.find { it.courseId == courseId }

    if (student == null) {
        println("Student not found: $studentId\n")
        return
    }

    if (course == null) {
        println("Course not found: $courseId\n")
        return
    }

    val alreadyEnrolled = enrollments.any { it.studentId == studentId && it.courseId == courseId }
    if (alreadyEnrolled) {
        println("Student ${student.name} is already enrolled in ${course.courseName}.\n")
        return
    }

    val enrollment = Enrollment(studentId = studentId, courseId = courseId)
    enrollments.add(enrollment)
    println("Enrolled ${student.name} in ${course.courseName}.\n")
}
