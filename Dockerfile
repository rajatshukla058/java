FROM tomcat:8-jre8
RUN mkdir shukla
COPY ./target/BiddingSystem-2.1.7.RELEASE.war /usr/local/tomcat/webapps
