@ECHO OFF
ECHO Starting geckodriver: %0 %*
c:\\geckodriver.exe --log fatal %* > NUL 2>&1