# Runnu 2016-2017
A 2-Dimensional maze game made with Java programming language

<img src="https://github.com/Tyndallray/Runnu/blob/master/res/Poster.png" alt="alt text" style="width:200;height:200">

![CodeSize](https://img.shields.io/github/languages/code-size/Tyndallray/Runnu)
![License](https://img.shields.io/github/license/Tyndallray/Runnu)
![Follow](https://img.shields.io/github/followers/Tyndallray?style=social)
![LastCommit](https://img.shields.io/github/last-commit/Tyndallray/Runnu)

<br />

# Setup

You must have MySQL server running on your machine
```
1. You can start off by installing XAMPP from (https://www.apachefriends.org/download.html)
2. After having it installed, you may open XAMPP control panel and run Apache and MySQL modules
3. Open any web browser and go to http://localhost/phpmyadmin/
4. Create a new Database and name it 'runnu'
5. Run (https://github.com/Tyndallray/Runnu/blob/master/runnu.sql) SQL Script to create the tables that you will need for the game inside runnu database that you just created
6. Your Database is ready!
```

Now it is time to make sure you have JDK installed. Because I had this project made a very long time ago, I believe I was using JDK 9 for it.
You may skip this part if you already have JDK installed [NOTE: If you might face any problems with the compilation, try to follow the steps]
```
1. Install an JDK 9 (outdated) version of JDK from (https://www.oracle.com/java/technologies/java-archive-javase9-downloads.html)
2. Set your path variable to have *JDK-9.x.x/bin* value in it [NOTE: Make sure you don't have any other JDKs already set to your path variable to avoid ambiguity]
3. You should be good to go!
```
<br/>

# Play Time!

## Instructions
   * Use Arrow Keys to move around
   * Use W,S,A,D to unlock doors and gather resources on the map
   * Use 'i' to toggle inventory

Finally, after having your database set up and JDK installed
```
1. Open Command prompt, and navigate to the project's root folder, i.e, */Runnu/*
2. Hit the following command to compile the code: javac *.java
3. Followed by this command to run the game: java -cp .;mysql-connector-java-5.1.48-bin.jar gameLaunch
4. I hope you like the game!
```

<img src="https://github.com/Tyndallray/Runnu/blob/master/res/runnu.gif" width="100%">

<br/>

## Author

* **Tyndallray**
    * **Github** - (https://github.com/Tyndallray)

## Licence
```
2-Dimensional Maze Game - Runnu
Copyright 2019 Tyndallray

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>
```
