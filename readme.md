The Clean Water Project
========

An ongoing crowd-sourced clean water tool for CS2340.

Set-Up (IntelliJ)
========

1. Import project from VCS
2. Create a folder called `out` in the root of project
3. File --> Project Structure --> Project Compiler Output --> ENTER `[...]/TheCLeanWaterProject/out`
4. Modules (on left pane of project structure) --> sources --> SELECT `src` --> Mark as `Sources`
5. Modules (on left pane of project structure) --> paths --> SELECT `out` --> Mark as `Excluded`
6. Modules (on left pane of project structure) --> paths --> SELECT `tests` --> Mark as `Test` & `Excluded`
7. Modules (on left pane of project structure) --> paths --> SELECT `inherit project output`
8. Navigate to src/tests/ and click on any test file. Hover over an @Test annotation, click on the light bulb and `import
    JUnit 4`.
9. File --> Project Structure --> Dependencies --> `+ symbol` --> jar --> dependencies/sqlite-jdbc-3.14.2.1.jar

Configuring Database
========
For testing purposes, use `database=test` in the config.properties file. We are currently using
SQLite for data persistence, and you can turn it on with `database=sqlite`

Adding GMaps Dependency
========
1. Copy the Gmaps xml file from dependencies into the libraries folder of the project
2. Navigate to Project Structure under File
3. Click on the '+' in the right hand corner and Add a Library
4. Navigate to Add a New Library and add the dependency file.
5. Apply Changes

Adding Apache Http Components Dependency
=======
1. Go to project structure
2. Go to global libraries
3. Add dependencies/httpcomponents-client-4.5.2/lib