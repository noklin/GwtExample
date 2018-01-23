package com.example;

import com.example.client.LauncherTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class LauncherSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Launcher");
    suite.addTestSuite(LauncherTest.class);
    return suite;
  }
}
