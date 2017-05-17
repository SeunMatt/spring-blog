package com.smatt.config;

/**
 * Created by smatt on 26/04/2017.
 */
public enum Roles {
    WRITER, //can only modify posts created by him
    EDITOR, //can modify any posts, but no admin privileges to the Users management
    SUPER_ADMIN; //can do just about anything
}


