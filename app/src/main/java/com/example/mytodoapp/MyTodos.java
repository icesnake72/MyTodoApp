package com.example.mytodoapp;

public class MyTodos {
    private Todos[] todos;

    public Todos[] getTodos ()
    {
        return todos;
    }

    public void setTodos (Todos[] todos)
    {
        this.todos = todos;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [todos = "+todos+"]";
    }
}
