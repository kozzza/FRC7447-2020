/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class IntakeCommand extends Command {
  public static int intakeDirection; //determines whether intake will spin in or out
  int intakeInput; //determines whether intake is on or off

  //intakeDirection: False means intake is sucking power cells, True means intake is shooting them
  public IntakeCommand() {
    intakeDirection = -1;
    requires(Robot.intakeSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override 
  protected void execute() {
    //you can replace the Y Button input with whatever the driver feels like. No command calls were added in the OI.
    if (Robot.oi.stick.getYButton() == false) {
      intakeInput= 0;
    }
    else if (Robot.oi.stick.getYButton() == true) {
      intakeInput = 1;
    }
    Robot.intakeSubsystem.intakeSpin(0.5f * intakeDirection * intakeInput);
    
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
  