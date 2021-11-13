#!/bin/bash

source set-environment.sh

cd kafka

bin/kafka-console-consumer.sh \
	--bootstrap-server localhost:$PORT_KAFKA \
	--topic baeldung \
	--from-beginning
