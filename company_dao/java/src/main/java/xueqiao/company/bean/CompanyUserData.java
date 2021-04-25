package xueqiao.company.bean;

import xueqiao.company.CompanyUser;
import xueqiao.company.GroupUser;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

import java.util.ArrayList;
import java.util.List;

public class CompanyUserData {
    private CompanyUser companyUser;
    private List<GroupUser> groupUserList;
    private List<TSyncTaskQueue> taskList;

    public CompanyUser getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(CompanyUser companyUser) {
        this.companyUser = companyUser;
    }

    public List<GroupUser> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(List<GroupUser> groupUserList) {
        this.groupUserList = groupUserList;
    }

    public void addtoGroupUserList(GroupUser groupUser) {
        if (this.groupUserList == null) {
            this.groupUserList = new ArrayList<>();
        }
        this.groupUserList.add(groupUser);
    }

    public List<TSyncTaskQueue> getTaskList() {
        return taskList;
    }

    public void addtoTaskList(TSyncTaskQueue task) {
        if (this.taskList == null) {
            this.taskList = new ArrayList<>();
        }
        this.taskList.add(task);
    }

    public void setTaskList(List<TSyncTaskQueue> taskList) {
        this.taskList = taskList;
    }
}
