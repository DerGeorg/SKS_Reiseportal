#!/bin/bash

source set-environment.sh

cd kafka

bin/kafka-console-consumer.sh \
	--bootstrap-server dergeorg.asuscomm.com:$PORT_KAFKA \
	--topic statscreate \
	--from-beginning
