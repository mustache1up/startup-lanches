#/bin/bash

cd /root/

nohup java -jar /root/startuplanches-core-0.0.1.jar &

service nginx start
