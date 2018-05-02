#!/bin/sh

# stop service
sudo service gui stop

# remove service
sudo rm -rfv /etc/init.d/gui

# remove jar
sudo rm -rfv /home/gui/gui.jar

# copy new jar
sudo cp -v my-strava-gui/target/my-strava-gui-0.0.1-SNAPSHOT.jar /home/gui/gui.jar

# set rights
sudo chown gui:apps /home/gui/gui.jar

# create service
sudo cp deploy/gui.service /etc/systemd/system

# start service
sudo service gui start