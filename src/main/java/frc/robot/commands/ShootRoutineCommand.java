/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import javax.sound.midi.Track;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ShootRoutineCommand extends Command {
  float percentVoltage = 0.9f;

  public static boolean teleopStarted;
  public ShootRoutineCommand() {
    requires(Robot.intakeSubsystem);
    requires(Robot.pneumaticShooterSubsystem);
    
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      setTimeout(7);
      teleopStarted = false;
      Robot.pneumaticShooterSubsystem.ShooterToggle(-1);
  }
  
  // Called repeatedly when this Command is scheduled to run
  @Override 
  protected void execute() {
    if (timeSinceInitialized() > 3) {
      System.out.println("past 3");
      Robot.intakeSubsystem.intakeSpin(percentVoltage, 1f);
    }
    Robot.driveTrain.manualDrive(0.25, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (isTimedOut() || teleopStarted);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("end");
    Robot.pneumaticShooterSubsystem.ShooterToggle(1);
    Robot.intakeSubsystem.intakeSpin(0, 1f);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
  