  
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
  TrackTargetCommand trackTargetCommand = new TrackTargetCommand(18);

  public AutonCommand() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      setTimeout(3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!isTimedOut()) {
        Robot.driveTrain.manualDrive(0.6, 0.24);
    }
    else {
        double tx = Limelight.getTx();
        System.out.println(tx);

        if ((tx < 5 && tx > -5) && Limelight.isTarget()) {
          trackTargetCommand.start();
        }
        else {
          Robot.driveTrain.manualDrive(0, -0.5);
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
    end();
  }
}