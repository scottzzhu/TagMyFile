# TagMyFile

An Android-based file managing system

## Idea

  TagMyFile focuses on user need on managing files and folders inside their devices. 
Managing files can be a difficult task for users, and the default file manager included in OS often become painful to use. Meanwhile, using tags for photos on Instagram is a nice idea. An idea comes to my mind. "Why not combining them both?" And the basic idea for TagMyFile was born. 
  The initial idea was planned on Windows. However, GUI Programming on Windows was far beyond my reach, and I gave up and moved on to Web-based project. My initial plan was to create the Web-based project with basic HTML+CSS+JavaScript, but it also proved too hard for me to finish in the limited time, since my plan requires a database system and an automatic-generating system for file detail pages, rather than only the basic tools in my initial plan. In the end, I chose to finish it with Android, which has several handy libraries to deal with problems encountered above, and is easy to create nice graphical interfaces.  

## Problem Scenarios

 + Search for files
 
 + Show detail of a file
 
 + Get all files with a tag from one file

 + Add new file into the manager
 
## Evaluation

  I did an in-class cognitive walkthrough for my evaluation process to see usefulness of the GUI. The result showed that some of the elements are duplicated, and I did the change that merged "search" activity and "show all result with tag" activity. Another feedback that I got from cognitive walkthrough is more hint for searching and adding. However, lacking of time and final week stopped me from finishing that part.

## Further Improvement Directions

  An improvement that can be made is detect file and automatically add into the manager. However, this need more permissions, and permission management could be painful to implement.
  Another feasible improvement is Auto-Tagging. For now, auto-tagging is not available because of my lack of experience and time. Some basic auto-tagging could be added, like parsing the file name or modify time to gather information for auto-tagging. With more advanced tools and algorithms like machine learning, more specific traits of files can be used to generate tags. 

## Acknowledgement

[Official Android Developers Guide](https://developer.android.com/)

[RecyclerView](https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465)

[Tag List](https://stackoverflow.com/questions/6996837/android-multi-line-linear-layout)

[Room Database](https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9 and https://stackoverflow.com/questions/44582397/android-room-persistent-library-typeconverter-error-of-error-cannot-figure-ou)
