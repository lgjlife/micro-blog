package com.microblog.search.service.dto;

import lombok.Data;

@Data
public class SearchUserDto {

    private long id;
    private String nickName;
    private String headerImgUrl;
    private int concersCount;
    private int blogCount;
    private String desc;


    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{

        private long id;
        private String nickName;
        private String headerImgUrl;
        private int concersCount;
        private int blogCount;
        private String desc;


        public Builder id(long id){
            this.id = id;
            return this;
        }
        public Builder nickName(String nickName){
            this.nickName = nickName;
            return this;
        }
        public Builder headerImgUrl(String headerImgUrl){
            this.headerImgUrl = headerImgUrl;
            return this;
        }
        public Builder concersCount(int concersCount){
            this.concersCount = concersCount;
            return this;
        }
        public Builder blogCount(int blogCount){
            this.blogCount = blogCount;
            return this;
        }
        public Builder desc(String desc){

            this.desc = desc;
            return this;
        }

        public SearchUserDto build(){
            SearchUserDto dto = new SearchUserDto();
            dto.setId(id);
            dto.setNickName(nickName);
            dto.setHeaderImgUrl(headerImgUrl);
            dto.setConcersCount(concersCount);
            dto.setBlogCount(blogCount);
            dto.setDesc(desc);
            return dto;
        }

    }

}
