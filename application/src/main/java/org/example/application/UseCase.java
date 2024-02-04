package org.example.application;

public abstract class UseCase<INT, OUT> {
   public abstract OUT execute(INT in);
}