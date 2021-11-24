#!/bin/bash

cd kafka

bin/kafka-console-consumer.sh \
	--bootstrap-server 84.112.88.28:$PORT_KAFKA \
	--topic statscreate \
	--from-beginning
