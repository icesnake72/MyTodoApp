package com.example.mytodoapp;

public class Todos {
    private String todo;

    private String created_time;

    private String user_id;

    private String id;

    private String done;

    public String getTodo ()
    {
        return todo;
    }

    public void setTodo (String todo)
    {
        this.todo = todo;
    }

    public String getCreated_time ()
    {
        return created_time;
    }

    public void setCreated_time (String created_time)
    {
        this.created_time = created_time;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDone ()
    {
        return done;
    }

    public void setDone (String done)
    {
        this.done = done;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [todo = "+todo+", created_time = "+created_time+", user_id = "+user_id+", id = "+id+", done = "+done+"]";
    }

}
