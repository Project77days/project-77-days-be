package org.example.infrastructure;

import org.example.application.UseCase;

public class Main {
  public static void main(String[] args) {
    System.out.println(new UseCase().execute());
  }
}