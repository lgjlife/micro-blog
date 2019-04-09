package com.microblog.blog.service.log;


import lombok.Builder;

@Builder
public class LogPojo {

    private  Long id;

    private  String datetime;

    private  String level;

    private  String thread;

    private  String method;
    private  String message;

    @Override
    public String toString() {
        
        return new  StringBuilder()
                .append("LogPojo{")
                .append("datetime='")
                .append( datetime ).append( '\'' )
                .append(", level='" )
                .append( level )
                .append( '\'')
                .append(", thread='" )
                .append( thread)
                .append( '\'' )
                .append(", method='" )
                .append( method )
                .append( '\'')
                .append(", message='")
                .append( message)
                .append( '\'' )
                .append('}').toString();
    }
}
