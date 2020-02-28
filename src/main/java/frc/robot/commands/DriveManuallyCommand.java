  
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
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class DriveManuallyCommand extends Command {

  double mmspeed = 0.55; //micromove speed
  double mtspeed = 0.43; //microturn speed
  double mspeed = 0.80; //move speed
  double tspeed = 0.625; //turn speed
  public static boolean toggle;

  public DriveManuallyCommand() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double move = Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kRight) - Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kLeft);
    double turn = Robot.oi.stick.getX(Hand.kLeft);
    double boost = -Robot.oi.stick.getY(Hand.kLeft)/2;  
    Robot.driveTrain.manualDrive(move * (mspeed + boost), turn * (tspeed + boost));
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