package xueqiao.company.bean;

import xueqiao.company.GroupUser;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

public class GroupUserData {
    private GroupUser groupUser;
    private TSyncTaskQueue syncTaskQueue;

    public GroupUser getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(GroupUser groupUser) {
        this.groupUser = groupUser;
    }

    public TSyncTaskQueue getSyncTaskQueue() {
        return syncTaskQueue;
    }

    public void setSyncTaskQueue(TSyncTaskQueue syncTaskQueue) {
        this.syncTaskQueue = syncTaskQueue;
    }
}
