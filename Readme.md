1. Pre-requisite - JDK 17 should be installed, maven should be installed
2. Clone repo - `git@github.com:nakulkumar14/staah-assignment.git`
3. Goto to the project folder where it is cloned : `cd ~/Downloads/staah-assignment`
4. Run the command - `mvn clean install`
5. Open the project in Intellij Idea to run it or you can run it from command line as well.
6. If running from Intellij Idea provide the runtime arguments as : `--hotels hotels.json --bookings bookings.json` and press run icon in HotelManager.java
7. To run it from command line, run using following command but replace the target classes path and json jar path with the location where the jar is downloaded through maven - `java -classpath /Users/nakulkumar/codebase/staah/target/classes:/Users/nakulkumar/.m2/repository/org/json/json/20250107/json-20250107.jar HotelManager --hotels hotels.json --bookings bookings.json`
8. Once program is running provide the input as mentioned in the assignment like `Availability(H1, 20240901, SGL)`, `Availability(H1, 20240901-20240903, DBL)` or `Search(H1, 365, SGL)`.
9. Enter a blank line to exit from the program.