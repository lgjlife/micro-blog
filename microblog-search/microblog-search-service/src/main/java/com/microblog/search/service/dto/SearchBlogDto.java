package com.microblog.search.service.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchBlogDto {


    private Long blogId;
    private Long userId;
    private String nickName;
    private Long publishTime;
    private String content;
    private String[] blogImg;
    private Integer collectNum;
    private Integer repostNum;
    private Integer comment;
    private Integer like;



    /*public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Long blogId;
        private Long userId;
        private String nickName;
        private Long publishTime;
        private String content;
        private String[] blogImg;
        private Integer collectNum;
        private Integer repostNum;
        private Integer comment;
        private Integer like;


        public Builder blogId(Long blogId){
            this.blogId = blogId;

            return this;
        }
        public Builder userId(Long userId){
            this.userId = userId;
            return this;
        }
        public Builder nickName(String nickName){
            this.nickName = nickName;
            return this;
        }
        public Builder publishTime(Long publishTime){
            this.publishTime = publishTime;
            return this;
        }
        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder blogImg(String[] blogImg){
            this.blogImg = blogImg;
            return this;
        }
        public Builder collectNum(Integer collectNum){
            this.collectNum = collectNum;
            return this;
        }
        public Builder repostNum(Integer repostNum){
            this.repostNum = repostNum;
            return this;
        }
        public Builder comment(Integer comment){
            this.comment = comment;
            return this;
        }
        public Builder like(Integer like){
            this.like = like;
            return this;
        }

        public SearchBlogDto build(){
            SearchBlogDto blogId = new SearchBlogDto();



            return blogId;


        }
    }*/

}
