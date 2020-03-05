/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int intakeMasterPort = 10;
  public static final int intakeSlavePort = 8;
// For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  public static int rightMaster = 4;
  public static int rightSlave0 = 5;
  public static int rightSlave1 = 6;
  public static int leftMaster = 1;
  public static int leftSlave0 = 2;
  public static int leftSlave1 = 3;


  public static int leftWinchMaster= 7;
  public static int rightWinchMaster = 8;
  

  public static int limelight = 7;

  public static int buttonAPort = 1;
  public static int buttonBPort = 2;
  public static int buttonYPort = 4;
  public static int buttonXPort = 3;

  public static int rightBumperPort = 6;
  public static int leftBumperPort = 5;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
