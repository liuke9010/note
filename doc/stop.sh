kill -9 `ps -ef | grep java | grep note-0.0.1-SNAPSHOT | awk '{print$2}'`
