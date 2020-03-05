  
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

public class TrackTargetCommand extends Command {
  double totalTurnError = 0;
  double lastTurnError = 0;
  double lastDistError = 0;

  float ktP = 0.05f;  // Proportional control constant for turn
  float ktI = 0.005f; // Integral control constant for turn
  float ktD = 0.05f; // Derivative control constant for turn

  float kmP = 0.02f; // Proportional control constant for move
  float kmD = 0.05f; // Deriviative control constant for move

  double targetDist;
  double arctanAngle;
  double distanceError;
  double combinedHeight = 81 - 22.5; //height between limelight and target

  double tx;
  double ty;
  boolean tv;
  boolean isFinished;

  Limelight limelight = new Limelight();

  public TrackTargetCommand(int targetDistParam) {
    requires(Robot.driveTrain);
    targetDist = targetDistParam;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    totalTurnError = 0;
    isFinished = false;
  } 

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    tx = Limelight.getTx();
    ty = Limelight.getTy();
    tv = Limelight.isTarget();

    arctanAngle = Math.atan(61.314+ ty);
    distanceError = (combinedHeight / arctanAngle) - targetDist;

    double dTurnError = (tx - lastTurnError);
    double dDistError = (distanceError - lastDistError);

    if (tx < 10 && tx > -10) {
      totalTurnError = totalTurnError + tx;
    }

    System.out.println("Distance Error: " + distanceError);
    double drivingAdjust = kmP * distanceError + kmD * dDistError;
    double turnAdjust = ktP*tx + ktD * dTurnError + ktI * totalTurnError;

    if (tv) {
      Robot.driveTrain.manualDrive(drivingAdjust, turnAdjust);
    }

    if ((distanceError < 1 && distanceError > -1) && (tx < 5 && tx > -5)) { //stop running if within 1 inch of target distance and 5 degrees of target
      isFinished = true;
    }

    lastTurnError = tx;
    lastDistError = distanceError;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }


  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}