package com.example.mytodoapp;

public class TodoResponse {

    private String email_id;

    private String nick_name;

    private String id;

    public String getEmail_id ()
    {
        return email_id;
    }

    public void setEmail_id (String email_id)
    {
        this.email_id = email_id;
    }

    public String getNick_name ()
    {
        return nick_name;
    }

    public void setNick_name (String nick_name)
    {
        this.nick_name = nick_name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [email_id = "+email_id+", nick_name = "+nick_name+", id = "+id+"]";
    }
}
