package com.makotogo.learn.javers;

/*
 * Copyright 2016 Makoto Consulting Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public class Person {

  public static final String STATE_DELIMITER = "~";

  public Person(String name, int age, EyeColor eyeColor, Gender gender) {
    this.name = name;
    this.age = age;
    this.eyeColor = eyeColor;
    this.gender = gender;
  }

  private String name;
  private int age;
  private EyeColor eyeColor;
  private Gender gender;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public EyeColor getEyeColor() {
    return eyeColor;
  }

  public void setEyeColor(EyeColor eyeColor) {
    this.eyeColor = eyeColor;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Person [name=" + name + ", age=" + age + ", height=" + ", eyeColor="
        + eyeColor
        + ", gender=" + gender + "]";
  }

}
