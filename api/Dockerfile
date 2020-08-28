# start with the tomcat parent image
FROM tomcat:8.5-jdk8-openjdk

#switch to the tomcat base dir
WORKDIR $CATALINA_HOME

# create an env variable that points to our war on the local machine
ARG WAR_FILE=./target/*.war
ARG DB_USER
ARG DB_PASS
#add the environment variables
ENV DB_USERNAME=$DB_USER
ENV DB_PASSWORD=$DB_PASS

#copy the war file to the webapps directory of tomcat
COPY $WAR_FILE ./webapps/ROOT.war

# expose port 8080
EXPOSE 8080

CMD ["catalina.sh", "run"]


