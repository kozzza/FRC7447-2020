  
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Limelight;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class DriveManuallyCommand extends Command {
  double mspeed = 0.80; //move speed
  double tspeed = 0.75; //turn speed

  double lastDistError = 0;

  float kmP = 0.002f; // Proportional control constant for move
  float kmD = 0.01f; // Deriviative control constant for move

  double tanAngle;
  double distanceError;
  double combinedHeight = 90.25 - 23.5; //height between limelight and target
  double drivingAdjust;

  double ty;
  boolean tv;

  Limelight limelight = new Limelight();

  public DriveManuallyCommand() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivingAdjust = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double move = Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kRight) - Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kLeft);
    double turn = Robot.oi.stick.getX(Hand.kLeft) * .8;
    double boost = -Robot.oi.stick.getY(Hand.kRight)/2;  
    
    //MARK STOP AUTON
    ty = Limelight.getTy();

    tanAngle = Math.tan(Math.toRadians(34.67 + ty));
    System.out.println(combinedHeight/tanAngle);
    distanceError = (combinedHeight / tanAngle);

    if (distanceError < 48) {
      double dDistError = (distanceError - lastDistError);
      if (tv) {
        System.out.println("Distance Error: " + distanceError);
        drivingAdjust = kmP * distanceError + kmD * dDistError;
        System.out.println("Driving Adjust: " + distanceError);
      }
      else {
        drivingAdjust = 0;
      }
    }
    else {
      drivingAdjust = 0;
    }

    Robot.driveTrain.manualDrive(drivingAdjust + (move * (mspeed + boost)), (turn * (tspeed + boost)));
    lastDistError = distanceError;
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
    end();
  }
}