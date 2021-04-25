package xueqiao.hosting.machine.daemon.report;

public interface ErrorReport {

    void reportInnerError();
    void reportOuterError();
}
