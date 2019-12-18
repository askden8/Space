Implement SpaceX rocket launches informative application.
As a user, I want to see the list of rockets, when I open the application
- For each rocket in the list, next attributes should be displayed: name, country and
engines count
- When the application is opened for the first time it should display the welcome dialog
As a user, I want to have possibility to filter the list and see only active rockets.
- Parameter “active” should be used for filtering data
As a user, when I press on the rocket in the list I want to see the next screen with rocket
description and selected rocket launches information
- Screen should be vertically scrollable
- In the beginning of the screen there should be a line graph showing the number of
launches per year
- After the graph there should be a description about chosen rocket
- After the description the list of chosen rocket launches should be grouped by year and
year should be displayed as a header
- For each launch should be displayed: mission name, launch date, was launch
successful or not and mission patch as an image
The application should display a loading indicator if it is fetching data.
The application should allow a user to refresh the data.
Bonus 1: All information should be cached for the future offline use.
Bonus 2: Animation added between screen transitions and on RecyclerView.
Bonus 3: Sticky headers for the years.
The application should use Open Source REST API that can be found here:
https://github.com/r-spacex/SpaceX-API
Please implement your solution as an application that we can try out. For Android we
need API 19 compatibility. Also send us the source code to your solution. Please provide
information for any third party library and tool you are using. Please use Kotlin as the
main language and MVVM architecture approach.
