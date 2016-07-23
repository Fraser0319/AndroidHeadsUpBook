#!/bin/bash

adb shell "run-as com.example.fraserbeaton.headsupchapter6starbuzzapp chmod -R 777 /data/data/com.example.fraserbeaton.headsupchapter6starbuzzapp/databases"

if [ `adb shell "if [ -e /sdcard/tempDB ]; then echo 1; fi"` ];then

  adb shell "cp -r /data/data/com.example.fraserbeaton.headsupchapter6starbuzzapp/databases/ /sdcard/tempDB/."

  adb pull sdcard/tempDB/ . adb shell "rm -r /sdcard/tempDB/*"

  echo tempDB already exists
else
    adb shell "mkdir -p /sdcard/tempDB"

    adb shell "cp -r /data/data/com.example.fraserbeaton.headsupchapter6starbuzzapp/databases/ /sdcard/tempDB/."

    adb pull sdcard/tempDB/ . adb shell "rm -r /sdcard/tempDB/*"

    echo created tempDB
fi
