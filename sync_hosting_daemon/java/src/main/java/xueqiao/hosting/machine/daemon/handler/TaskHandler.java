package xueqiao.hosting.machine.daemon.handler;

import org.apache.thrift.TException;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

public interface TaskHandler {
    void handle(TSyncTaskQueue syncTaskQueue) throws TException;
}
