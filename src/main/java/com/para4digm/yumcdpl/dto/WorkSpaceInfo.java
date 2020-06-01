package com.para4digm.yumcdpl.dto;

public class WorkSpaceInfo {
    private String workSpaceId;
    private String workSpaceName;
    private String userId;
    private String userName;
    private String description;
    private boolean allWorkspaceFlg;

    public String getWorkSpaceId() {
        return workSpaceId;
    }

    public void setWorkSpaceId(String workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    public String getWorkSpaceName() {
        return workSpaceName;
    }

    public void setWorkSpaceName(String workSpaceName) {
        this.workSpaceName = workSpaceName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllWorkspaceFlg() {
        return allWorkspaceFlg;
    }

    public void setAllWorkspaceFlg(boolean allWorkspaceFlg) {
        this.allWorkspaceFlg = allWorkspaceFlg;
    }
}
