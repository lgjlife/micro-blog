package com.microblog.filesystem.anno;

import com.microblog.filesystem.type.FSType;

public @interface EnableFilesystem {
    FSType type() default FSType.LOCAL_TYPE;
}
