#!/bin/sh
nohup java -jar note-0.0.1-SNAPSHOT.jar -Xms64m -Xmx128m -XX:PermSize=64m -XX:MaxPermSize=128m >>logs/note.out 2>&1 &
