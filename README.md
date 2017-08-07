# Spark - Test

This Spark project can be used to answer five test questions about processing log records with format used since the 1990s.

## Questions

1. Number of unique hosts;
2. Total of 404 errors;
3. Top 5 URLs Paths resulting in 404 errors;
4. Total of 404 erros by day;
5. Total of bytes returned.

## Building

This is a Maven project, so just package the project

## Execution

After building the JAR, send it to Spark cluster and use the command 'spark-submit'

## Notice

It is necessary to copy the log files (access_log_Aug95 and access_log_Jul95) to directory "\src\main\resources"