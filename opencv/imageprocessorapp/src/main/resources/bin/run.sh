#!/usr/bin/env bash

SCRIPT_DIR="$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

java -classpath "${SCRIPT_DIR}/../lib/*:." com.github.zaolahma.Main