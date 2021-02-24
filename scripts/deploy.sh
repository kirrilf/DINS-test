#!/usr/bin/env bash

mvn clean package

echo 'Copy files'

scp -i ~/.ssh/id_rsa \
    target/DINS-test-1.0-SNAPSHOT.jar \
    kirrilf@34.105.180.71:/home/kirrilf/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa kirrilf@34.105.180.71 << EOF

pgrep java | xargs kill -9
nohup java -jar DINS-test-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'End'
