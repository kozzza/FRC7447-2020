  
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
  double totalMoveError = 0;
  double lastTurnError = 0;
  double lastDistError = 0;

  float ktP = 0.03f;  // Proportional control constant for turn
  float ktI = 0.00f; // Integral control constant for turn
  float ktD = 0.07f; // Derivative control constant for turn

  float kmP = 0.013f; // Proportional control constant for move
  float kmI = 0.003f;
  float kmD = 0.009f; // Deriviative control constant for move

  double targetDist;
  double tanAngle;
  double distanceError;
  double combinedHeight = 91 - 26.5; //height between limelight and target

  double tx;
  double ty;
  boolean tv;
  public static boolean isFinished;
  boolean isAuton;

  Limelight limelight = new Limelight();
  ShootRoutineCommand shootRoutineCommand = new ShootRoutineCommand();

  public TrackTargetCommand(int targetDistParam, boolean isAutonParam) {
    requires(Robot.driveTrain);
    targetDist = targetDistParam;
    isAuton = isAutonParam;
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

    tanAngle = Math.tan(Math.toRadians(48.54 + ty));
    System.out.println(combinedHeight/tanAngle);
    distanceError = (combinedHeight / tanAngle) - targetDist;

    double dTurnError = (tx - lastTurnError);
    double dDistError = (distanceError - lastDistError);

    if (tx < 10 && tx > -10) {
      totalTurnError = totalTurnError + tx;
    }
    if (distanceError < 18) {
      totalMoveError = totalMoveError + distanceError;
    }

    System.out.println("Distance Error: " + distanceError);
    double drivingAdjust = kmP * distanceError + kmD * dDistError + kmI * totalMoveError;
    double turnAdjust = ktP * tx + ktD * dTurnError + ktI * totalTurnError;

    if (tv) {
      Robot.driveTrain.manualDrive(drivingAdjust*0.75, turnAdjust);
    }

    if ((distanceError < 5 && distanceError > -5) && (tx < 5 && tx > -5)) { //stop running if within 1 inch of target distance and 5 degrees of target
      isFinished = true;
      System.out.println("FINISHED");
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
    if (isAuton) {
      shootRoutineCommand.start();
    }
  }


  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}