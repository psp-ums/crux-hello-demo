# crux-hello-demo
This a simple demo  application that returns a hello world greeting on a HTTP GET and prints the greeting in the log on a HTTP POST. To make it a bit more interesting there are a few complications added:

* Service oriented. The helloservice bundle exposes the HelloService API using the Crux service repository. 
* Configuration. Muli language support to demonstrate simple configuration features. See how the class HelloServiceConfig relates to  CruxConsole (Config  -> HelloService).
* StateOfHealth. SOH have been added to detect requests for languages with missing greeting configuration. E.g try lang=DK and see that a SOH warning is triggered. The class Selftester plugs into the SOH framwork (by extending Selftestable and being in the bundle context). Configuration and monitoring for SOH is found in CruxConsole.
* Queing. Crux Queue is used to separate the actual message printing. The class PrintQueueReceiver consumes from the queue and queue producing happens in HelloServiceImpl.
* Monitoring. The applications have some monitoring, i.e instrumentation that plugs into the Crux monitoring framework. See class Monitoring and how it relates to the CruxConsole (Monitor -> Message statistics).
* Timer. A simple timer task is added to demonstrate how a periodic timer task can be added (HelloTimer). See more about timers in CruxConsole configuration and moniotoring.

# Platforms
Running on Crux 3.9.1 and newer and Java 11

# Concluence
See https://path.everbridge.com/display/MS/Getting+started+with+Crux%3A+Hello+World+application
