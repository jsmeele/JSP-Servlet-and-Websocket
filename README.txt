Requirements
1) Software
1.1) JDK 8
1.2) Servlet 3.1
1.3) IDE (Such as Eclipse e.g. Neon)
1.4) Apache Tomcat 8.5.13

2) To configure the run time in Eclipse Neon (Old version without Tomcat 8.5, after update not required), see instructions below:
	There is a patch for Eclipse NEON:
	https://bugs.eclipse.org/bugs/attachment.cgi?id=262418&action=edit

	Download this patch and put it to the plugins directory of your Eclipse installation. It will replace the default 	"org.eclipse.jst.server.tomcat.core_1.1.800.v201602282129.jar".

	NOTE
	After you add this patch you must choose "Apache Tomcat v9.0" when adding a server runtime environment in the Eclipse (Preferences > Server > Runtime Environments).
	I.e. this patch allows you to select either Tomcat version 9.x or Tomcat version 8.5.x when adding Apache Tomcat v.9.0 runtime environment.

Initial Edit
1) Serversocket to receive files from other sources
2) Demo client to sent a file
3) Constants loaded from properties
3.1) Windows is only added, other OS are prepared and not coded
3.2) Used to configure the application
4) Very simple sample Servlet and JSP
4.1) TODO: This is the one for the final version