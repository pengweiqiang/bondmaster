package com.huake.bondmaster.model.bean;

import java.util.List;

/**
 * @author will on 2017/8/31 22:39
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class CommentBean {

    private long commentNum;//评论次数

    private List<Comment> commentList;

    public long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(long commentNum) {
        this.commentNum = commentNum;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public class Comment{
        private String id;
        private String userId;
        private String name;
        private String content;
        private String createDate;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

}
