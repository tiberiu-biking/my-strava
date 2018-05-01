#!/bin/sh

# stop service
sudo service gui stop

# remove service
sudo rm -rfv /etc/init.d/gui

# remove jar
rm -rfv /home/gui/gui.jar

# copy new jar
cp my-strava-gui/target/my-strava-gui-0.0.1-SNAPSHOT.jar /home/gui/gui.jar

# create service
sudo ln -s /home/gui/gui.jar /etc/init.d/gui

# start service
sudo service gui stop