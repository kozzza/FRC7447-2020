/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Limelight;
import frc.robot.Robot;

public class DistanceAdjustCommand extends Command {
  double kPDistance = 0.45; //NOTE: THIS IS SUPPOSED TO BE A FLOAT
  float kIDistance = 0.02f;
  double targetDist = 10;
  double totalError;
  double tangentAngle;
  double distanceError;
  double drivingAdjust;
  double ty;
  boolean tv;
  double angleTwo;
  double heightOne = 0.625; //height of limelight above ground
  double heightTwo = 7.9583; //height of CENTER of target above ground (in room 309)
  double combinedHeight = heightTwo - heightOne; //height between limelight and target
  Limelight limelight = new Limelight();

  public DistanceAdjustCommand() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    totalError = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("-----------------------");
    ty = Limelight.getTy();
    tv = Limelight.isTarget();
    tangentAngle = Math.tan(Math.toRadians(Math.atan((heightTwo-heightOne)/targetDist)) + Math.toRadians(ty)); //tangent of the combined angle (substitute for angle was 36.1)

    distanceError = (combinedHeight / tangentAngle) - targetDist; //target is targetDist
    if (distanceError < 1 && distanceError > -1) {
      //totalError = totalError + distanceError;
    }
    System.out.println("Te: " + totalError);
    System.out.println("Distance Error: " + distanceError);
    drivingAdjust = kPDistance * distanceError + totalError*kIDistance;
    System.out.println("Tan: " + tangentAngle);
    System.out.println("The driving adjust is " + drivingAdjust);
    if (tv) {
      System.out.println("Valid Target");
      Robot.driveTrain.manualDrive(drivingAdjust, 0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
