package ums.cruxscalademo.helloservice.internal.queue

import cellvision.coreutil.annotation.Execute
import cellvision.crux.queue.{CruxQueueReceiver, QueueId}
import org.apache.commons.logging.Log
import ums.cruxscalademo.helloservice.HelloMessage

/**
  * Queue receiver that listens to the "PRINT"-queue and processes messages. This receiver register itself due to the
  *
  * @CruxQueueReceiver annotation being detected by Crux in the bundle context.
  * @author psp
  * @since 2016.07.14
  */
@CruxQueueReceiver(queueId = PrintQueueReceiver.queueName)
class PrintQueueReceiver(logger: Log) {

  @Execute
  def receive(message: HelloMessage): Unit = {
    logger.info(message.message)
  }
}

object PrintQueueReceiver {
  final val queueName = "Q_PRINT"
  final val queueId = new QueueId(queueName)
}