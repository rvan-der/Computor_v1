#!/bin/bash

find ./fr/rvander/computorV1 -name "*.class" -exec rm {} +
javac ./fr/rvander/computorV1/ComputorV1.java