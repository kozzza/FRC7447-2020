/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PneumaticCommand extends Command {


  public PneumaticCommand() {
    requires(Robot.pneumaticSubsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("coall;");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    final int kDoubleSolenoidForward = 4;
    final int kDoubleSolenoidReverse = 3;
    
    boolean solenoidon = Robot.oi.stick.getRawButton(kDoubleSolenoidForward);
    boolean solenoidoff = Robot.oi.stick.getRawButton(kDoubleSolenoidReverse);

    Robot.pneumaticSubsystem.Solenoidonoff(solenoidon, solenoidoff);

  
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
