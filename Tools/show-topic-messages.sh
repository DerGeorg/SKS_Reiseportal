#!/bin/bash

source set-environment.sh

cd kafka

bin/kafka-console-consumer.sh \
	--bootstrap-server 192.168.1.4:$PORT_KAFKA \
	--topic statscreate \
	--from-beginning
