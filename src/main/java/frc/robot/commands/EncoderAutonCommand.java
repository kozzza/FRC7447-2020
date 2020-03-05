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

public class EncoderAutonCommand extends Command {
  Limelight limelight = new Limelight();
  TrackTargetCommand trackTargetCommand = new TrackTargetCommand(18);
  double targetDist = 12;
  double brakeVoltage = 0.2;
  public EncoderAutonCommand() {
    requires(Robot.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { //Robot drives backward to pass initiation line and then drives forward up to Power Port
    if (!isTimedOut()) {
      Robot.driveTrain.manualDrive(-0.2, 0);
    }
    else {
      if ((Robot.encoderLeft.getDistance() < targetDist) && (Robot.encoderRight.getDistance() < targetDist)){
        Robot.driveTrain.manualDrive(0.2, 0);
      }
      else {
        Robot.driveTrain.manualDrive(brakeVoltage, 0);
        brakeVoltage *= 0.7;
        /*double tx = Limelight.getTx();
        System.out.println(tx);
  
        if ((tx < 5 && tx > -5) && Limelight.isTarget()) {
          trackTargetCommand.start();
        }
        else {
          Robot.driveTrain.manualDrive(0, -0.5);*/
      }
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
