# start with the tomcat parent image
FROM tomcat:8.5-jdk8-openjdk

#switch to the tomcat base dir
WORKDIR $CATALINA_HOME

# create an env variable that points to our war on the local machine
ARG WAR_FILE=./target/*.war

#copy the war file to the webapps directory of tomcat
COPY $WAR_FILE ./webapps

# expose port 8080
EXPOSE 8080

CMD ["catalina.sh", "run"]

#image definition complete

