The Clean Water Project
========

An ongoing crowd-sourced clean water tool for CS2340.

Set-Up (IntelliJ)
========

1. Import project from VCS
2. Create a folder called out in the root of project
3. File --> Project Structure --> Project Compiler Output --> ENTER [...]/TheCLeanWaterProject/out
4. Modules (on left pane of project structure) --> sources --> SELECT src --> Mark as Sources
5. Modules (on left pane of project structure) --> paths --> SELECT out --> Mark as excluded
6. Modules (on left pane of project structure) --> paths --> SELECT inherit project output
7. Navigate to src/tests/ and click on any test file. Hover over an @Test annotation, click on the light bulb and import
    JUnit 4.