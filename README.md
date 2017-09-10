universal-breakfast
===================

SETUP OPENSHIFT V3 PROD (once and for all)
-----------------------

#### Setup local tooling
* install from https://docs.openshift.com/enterprise/3.1/cli_reference/get_started_cli.html
* ```$ oc login https://api.starter-us-west-1.openshift.com``̀
* ```$ oc new-build --image-stream=openshift/jboss-webserver30-tomcat8-openshift:latest --binary=true --name=universal-breakfast-binary``̀
* Update universal-breakfast Deployments with universal-breakfast-binary docker image on web console
#### Setup openshift env vars
* ```$ oc env dc universal-breakfast MYSQL_USER="xxxx"``̀
* ```$ oc env dc universal-breakfast MYSQL_PASSWORD="xxxx"``̀
* ```$ oc env dc universal-breakfast MAIL_PASSWORD="xxxx"``̀
* ```$ oc env dc universal-breakfast UB_SALT="xxxx"``̀
* ```$ oc env dc universal-breakfast JAVA_OPTIONS="-Xms512m -Xmx512m"``̀
#### Setup database
* ```$ mv <ub.sql> tmp/```
* ```$ oc get pods```
* ```$ oc rsync tmp/ <mysql_pod_name>:/var/lib/mysql/data```
* ```$ oc rsh <mysql_pod>```
* ```$ cd /var/lib/mysql/data/tmp/;mysql -u root;use ub;source ub.sql```

BUILD
-----

* ```$ sdk install grails 2.3.11```
* ```$ grails war -Dgrails.env=production```
* ```$ oc start-build universal-breakfast-binary --from-file=target/ROOT.war```
* Access to : http://universal-breakfast-universal-breakfast.a3c1.starter-us-west-1.openshiftapps.com/universal-breakfast/
