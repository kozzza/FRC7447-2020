  
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Limelight;

public class AutonCommand extends Command {
  Limelight limelight = new Limelight();
  TrackTargetCommand trackTargetCommand = new TrackTargetCommand(24, true);

  public AutonCommand() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      setTimeout(2);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      double tx = Limelight.getTx();
      System.out.println(tx);

      if (!isTimedOut()) {
        Robot.driveTrain.manualDrive(-0.3, 0);
      }
      else if (Limelight.isTarget()) {
        trackTargetCommand.start();
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
    end();
  }
}