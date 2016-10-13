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
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

  Logger log = Logger.getLogger(AppTest.class.getName());

  /**
   * The class under test - App
   */
  private App classUnderTest;

  /**
   * The Javers object
   */
  private Javers javers;

  @Before
  public void setUp() throws Exception {
    classUnderTest = new App();
    javers = JaversBuilder.javers().build();
  }

  @After
  public void tearDown() throws Exception {
    classUnderTest = null;
  }

  @Test
  public void testCreateEmployees() {
    int numberOfEmployeesToCreate = 10;
    List<Employee> employees = classUnderTest.createEmployees(numberOfEmployeesToCreate);

    assertEquals(numberOfEmployeesToCreate, employees.size());
  }

  @Test
  public void testJaversDiff_ChangesSummary_Employee() {
    int numberOfEmployeesToCreate = 2;
    List<Employee> employees = classUnderTest.createEmployees(numberOfEmployeesToCreate);
    assertEquals(numberOfEmployeesToCreate, employees.size());
    Employee e1 = employees.get(0);
    log.info("Employee 1: " + ReflectionToStringBuilder.toString(e1));
    Employee e2 = employees.get(1);
    log.info("Employee 2: " + ReflectionToStringBuilder.toString(e2));

    Diff diff = javers.compare(e1, e2);
    log.info("Changes summary: " + diff.prettyPrint());
  }

  @Test
  public void testJaversDiff_ValueChange_Employee() {
    int numberOfEmployeesToCreate = 1;
    List<Employee> employees = classUnderTest.createEmployees(numberOfEmployeesToCreate);
    assertEquals(numberOfEmployeesToCreate, employees.size());
    Employee e1 = employees.get(0);
    log.info("Employee 1: " + ReflectionToStringBuilder.toString(e1));
    Employee e2 =
        new Employee(e1.getName(), e1.getAge(), e1.getEyeColor(), e1.getGender(), e1.getTin(), e1.getEmployeeNumber());
    e2.setAge(e2.getAge() + 2);
    log.info("Employee 2: " + ReflectionToStringBuilder.toString(e2));
    Diff diff = javers.compare(e1, e2);
    List<ValueChange> changes = diff.getChangesByType(ValueChange.class);
    for (ValueChange change : changes) {
      log.info("Change => Property name/e1/e2: " + change.getPropertyName() + "/"
          + change.getLeft().toString() + "/" + change.getRight().toString());
    }
  }

}
