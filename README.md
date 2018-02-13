# Chicago-Landmarks

For this project I have designed and coded two new Android apps meant to work together on an Android Nexus 9
tablet running Nougat (API level 25). Here is a short summary of the apps:


Application A1 consists of a single activity containing two fragments. The first fragment consists of a list
naming at least 6 Chicago landmarks. Interactive app users can select one of the landmarks. When this happens,
the selected item stays highlighted and the second fragment displays the web page of the highlighted item. This
app also defines an options menu with at least two items: (1) exiting A1 and (2) launching the next application,
A2. A1 launches A2 by sending a system broadcast. In addition, application A1 maintains an action bar. The
action bar shows the name of the application (your choice) and the overflow area.

Application A2 is a picture repository. The gallery shows images from each of the landmark listed in application
A1. A1 first starts the gallery in A2 by sending a global broadcast; A2 contains a broadcast receiver with
appropriate filters to catch A1’s broadcast. When it receives a broadcast, A2 launches the gallery even though
A2 was not running before A1 sent the broadcast. In addition, A2 defines a new, dangerous-level permission. The
broadcast receiver contained in A2 requires that the broadcast sender (e.g., application A1) own this permission
in order to respond to a broadcast. 								 			

